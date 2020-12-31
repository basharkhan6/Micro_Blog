package com.workspaceit.microblog.repository;

import com.workspaceit.microblog.model.Vote;
import com.workspaceit.microblog.model.enumeration.VoteType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Integer> {

    long countByPostIdAndType(VoteType type, int postId);

    Vote findByPostIdAndVoterEmail(int postId, String voterEmail);

}
