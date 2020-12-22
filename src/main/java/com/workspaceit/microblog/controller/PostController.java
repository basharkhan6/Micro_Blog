package com.workspaceit.microblog.controller;

import com.workspaceit.microblog.model.Post;
import com.workspaceit.microblog.service.PostService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/api/posts")
    public void createPost(@RequestBody @Valid Post post) {
        postService.createPost(post);
    }

    @GetMapping("/api/posts")
    public List<Post> getAllPost() {
        return postService.findAllPost();
    }

    @GetMapping("/api/posts/{id}")
    public Post getPost(@PathVariable int id) {
        return postService.findPost(id);
    }
}
