package com.workspaceit.microblog.service.implementation;

import com.workspaceit.microblog.model.Post;
import com.workspaceit.microblog.model.User;
import com.workspaceit.microblog.repository.PostRepository;
import com.workspaceit.microblog.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }


    @Override
    public void createPost(Post post) {
        post.setCreatedAt(LocalDateTime.now());
        postRepository.save(post);
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
}
