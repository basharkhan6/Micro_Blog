package com.workspaceit.microblog.controller;

import com.workspaceit.microblog.model.Comment;
import com.workspaceit.microblog.model.Post;
import com.workspaceit.microblog.model.Vote;
import com.workspaceit.microblog.model.enumeration.VoteType;
import com.workspaceit.microblog.service.PostService;
import com.workspaceit.microblog.service.VoteService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
public class PostController {

    private final PostService postService;
    private final VoteService voteService;

    public PostController(PostService postService, VoteService voteService) {
        this.postService = postService;
        this.voteService = voteService;
    }

    @PostMapping("/api/posts")
    public Post createPost(@RequestBody @Valid Post post,
                           Principal user) {
        return postService.createPost(post, user.getName());
    }

    @GetMapping("/api/posts")
    public List<Post> getAllPost() {
        return postService.findAllPost();
    }

    @GetMapping("/api/posts/{id}")
    public Post getPost(@PathVariable int id) {
        return postService.findPost(id);
    }


    @PostMapping("/api/posts/{id}/comments")
    public Comment makePostComment(@PathVariable int id,
                                @RequestBody @Valid Comment comment,
                                Principal user) {
        return postService.makeComment(comment, id, user.getName());
    }

    @GetMapping("/api/posts/{id}/comments")
    public List<Comment> getPostComment(@PathVariable int id) {
        return postService.findAllComments(id);
    }

    @PostMapping(value = "/api/posts/{id}/votes", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Vote saveVote(@PathVariable int id,
                         @RequestBody VoteType type,
                         Principal user) {
        return voteService.saveVote(type, id, user.getName());
    }

//    @GetMapping("/api/posts/{id}/votes/{type}")
//    public long countVote(@PathVariable int id,
//                          @PathVariable VoteType type) {
//        return voteService.countVote(type);
//    }
}
