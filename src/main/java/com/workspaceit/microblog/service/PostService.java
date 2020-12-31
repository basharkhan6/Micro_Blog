package com.workspaceit.microblog.service;

import com.workspaceit.microblog.model.Comment;
import com.workspaceit.microblog.model.Post;
import com.workspaceit.microblog.model.User;

import java.util.List;

public interface PostService {

    Post createPost(Post post, String authorEmail);

    Post findPost(int id);

    List<Post> findAllPostByAuthor(User author);

    List<Post> findAllPost();

    Comment makeComment(Comment comment, int postId, String commenterEmail);

    List<Comment> findAllComments(int postId);

}
