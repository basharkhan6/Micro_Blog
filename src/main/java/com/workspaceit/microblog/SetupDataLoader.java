package com.workspaceit.microblog;

import com.workspaceit.microblog.model.Comment;
import com.workspaceit.microblog.model.Post;
import com.workspaceit.microblog.model.User;
import com.workspaceit.microblog.model.Vote;
import com.workspaceit.microblog.model.enumeration.VoteType;
import com.workspaceit.microblog.repository.CommentRepository;
import com.workspaceit.microblog.repository.PostRepository;
import com.workspaceit.microblog.repository.UserRepository;
import com.workspaceit.microblog.repository.VoteRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private boolean alreadySetup;
    private final PasswordEncoder encoder;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final VoteRepository voteRepository;

    public SetupDataLoader(PasswordEncoder encoder, UserRepository userRepository, PostRepository postRepository, CommentRepository commentRepository, VoteRepository voteRepository) {
        alreadySetup = false;
        this.encoder = encoder;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.voteRepository = voteRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup) {
            return;
        }
        User user = createUserIfNotFound("test@gmail.com", "123456", "Test Name");
        User user2 = createUserIfNotFound("test2@gmail.com", "1234567", "Test Name2");
        Post post = savePost("Post Title", "A big description", user);
        makeComment("Very useful post", post, user);
        saveVoteIfNotFound(VoteType.UPVOTE, post, user);


        alreadySetup = true;
    }

    private User createUserIfNotFound(String email, String password, String name) {
        return userRepository.findByEmailIgnoreCase(email)
                .orElseGet(() -> { return userRepository.save(new User(email, encoder.encode(password), name)); });
    }

    private Post savePost(String title, String description, User author) {
        return postRepository.save(new Post(title, description, LocalDateTime.now(), author));
    }

    private Comment makeComment(String comment, Post post, User user) {
        return commentRepository.save(new Comment(comment, LocalDateTime.now(), post, user));
    }

    private Vote saveVoteIfNotFound(VoteType type, Post post, User voter) {
        Vote vote = voteRepository.findByPostIdAndVoterEmail(post.getId(), voter.getEmail());
        if (vote == null) {
            vote = new Vote();
            vote.setType(type);
            if (VoteType.UPVOTE.equals(type)) {
                post.setUpVote(post.getUpVote() + 1);
            } else {
                post.setDownVote(post.getDownVote() + 1);
            }
            vote.setPost(post);
            vote.setVoter(voter);
            postRepository.save(post);
            return voteRepository.save(vote);
        }

        return vote;
    }
}
