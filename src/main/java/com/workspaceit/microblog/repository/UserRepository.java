package com.workspaceit.microblog.repository;

import com.workspaceit.microblog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    boolean existsUserByEmail(String email);

    Optional<User> findByEmailIgnoreCase(String email);

}
