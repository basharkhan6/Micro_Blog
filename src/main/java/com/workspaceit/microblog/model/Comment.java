package com.workspaceit.microblog.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotEmpty(message = "Comment can not be empty")
    private String text;

    @Column(nullable = false)
    private LocalDateTime commentedAt;

    @ManyToOne
    @JsonIgnore
    private Post post;

    @ManyToOne
    private User commenter;


    public Comment() {
    }

    public Comment(@NotEmpty(message = "Comment can not be empty") String text, LocalDateTime commentedAt, Post post, User commenter) {
        this.text = text;
        this.commentedAt = commentedAt;
        this.post = post;
        this.commenter = commenter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getCommentedAt() {
        return commentedAt;
    }

    public void setCommentedAt(LocalDateTime commentedAt) {
        this.commentedAt = commentedAt;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public User getCommenter() {
        return commenter;
    }

    public void setCommenter(User commenter) {
        this.commenter = commenter;
    }
}
