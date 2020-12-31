package com.workspaceit.microblog.service.implementation;

import com.workspaceit.microblog.model.Comment;
import com.workspaceit.microblog.model.Post;
import com.workspaceit.microblog.model.User;
import com.workspaceit.microblog.repository.CommentRepository;
import com.workspaceit.microblog.repository.PostRepository;
import com.workspaceit.microblog.repository.UserRepository;
import com.workspaceit.microblog.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public PostServiceImpl(UserRepository userRepository, PostRepository postRepository, CommentRepository commentRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public Post createPost(Post post, String authorEmail) {
        post.setCreatedAt(LocalDateTime.now());
        post.setAuthor(userRepository.findByEmailIgnoreCase(authorEmail).get());
        return postRepository.save(post);
    }

    @Override
    public Post findPost(int id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public List<Post> findAllPostByAuthor(User author) {
        return postRepository.findAllByAuthor(author);
    }

    @Override
    public List<Post> findAllPost() {
        return postRepository.findAll();
    }

    @Override
    public Comment makeComment(Comment comment, int postId, String commenterEmail) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        comment.setPost(post);
        comment.setCommenter(userRepository.findByEmailIgnoreCase(commenterEmail).get());
        comment.setCommentedAt(LocalDateTime.now());
        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> findAllComments(int postId) {
        return commentRepository.findAllByPostId(postId);
    }
}
