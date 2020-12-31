package com.workspaceit.microblog.service.implementation;

import com.workspaceit.microblog.model.Post;
import com.workspaceit.microblog.model.User;
import com.workspaceit.microblog.model.Vote;
import com.workspaceit.microblog.model.enumeration.VoteType;
import com.workspaceit.microblog.repository.PostRepository;
import com.workspaceit.microblog.repository.UserRepository;
import com.workspaceit.microblog.repository.VoteRepository;
import com.workspaceit.microblog.service.VoteService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class VoteServiceImpl implements VoteService {

    private final VoteRepository voteRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public VoteServiceImpl(VoteRepository voteRepository, PostRepository postRepository, UserRepository userRepository) {
        this.voteRepository = voteRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Vote saveVote(VoteType type, int postId, String voterEmail) {
        Vote vote = voteRepository.findByPostIdAndVoterEmail(postId, voterEmail);
        if (vote == null) {
            vote = new Vote();
            Post post = postRepository.findById(postId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
            if (VoteType.UPVOTE.equals(type)) {
                post.setUpVote(post.getUpVote() + 1);
            } else {
                post.setDownVote(post.getDownVote() + 1);
            }
            vote.setPost(post);
            vote.setVoter(userRepository.findByEmailIgnoreCase(voterEmail).get());
            vote.setType(type);
            postRepository.save(post);
            return voteRepository.save(vote);
        } else {
            if (vote.getType() != type) {
                Post post = postRepository.findById(postId)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
                if (VoteType.UPVOTE.equals(type)) {
                    post.setUpVote(post.getUpVote() + 1);
                    post.setDownVote(post.getDownVote() - 1);
                } else {
                    post.setUpVote(post.getUpVote() - 1);
                    post.setDownVote(post.getDownVote() + 1);
                }
                vote.setType(type);
                postRepository.save(post);
                return voteRepository.save(vote);
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
        }
    }

    @Override
    public long countVote(VoteType type, int postId) {
        return voteRepository.countByPostIdAndType(type, postId);
    }
}
