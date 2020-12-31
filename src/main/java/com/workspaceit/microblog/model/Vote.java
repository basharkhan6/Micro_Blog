package com.workspaceit.microblog.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.workspaceit.microblog.model.enumeration.VoteType;

import javax.persistence.*;

@Entity
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Enumerated(value = EnumType.STRING)
    private VoteType type;

    @ManyToOne
    private Post post;

    @ManyToOne
    private User voter;

    public Vote() {
    }

    public Vote(VoteType type, Post post, User voter) {
        this.type = type;
        this.post = post;
        this.voter = voter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public VoteType getType() {
        return type;
    }

    public void setType(VoteType type) {
        this.type = type;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public User getVoter() {
        return voter;
    }

    public void setVoter(User voter) {
        this.voter = voter;
    }

}
