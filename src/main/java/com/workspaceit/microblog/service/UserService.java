package com.workspaceit.microblog.service;

import com.workspaceit.microblog.model.User;

import java.util.List;

public interface UserService {

    boolean emailExist(String email);

    void createUser(User user);

    User findUser(int id);

    User findUser(String email);

    List<User> findAllUser();

}
