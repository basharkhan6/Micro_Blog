package com.workspaceit.microblog.service;

import com.workspaceit.microblog.model.Post;
import com.workspaceit.microblog.model.User;

import java.util.List;

public interface PostService {

    void createPost(Post post);

    Post findPost(int id);

    List<Post> findAllPostByAuthor(User author);

    List<Post> findAllPost();

}
