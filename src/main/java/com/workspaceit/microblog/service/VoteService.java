package com.workspaceit.microblog.service;

import com.workspaceit.microblog.model.Vote;
import com.workspaceit.microblog.model.enumeration.VoteType;

public interface VoteService {

    Vote saveVote(VoteType type, int postId, String voterEmail);

    long countVote(VoteType type, int postId);

}
