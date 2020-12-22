package com.workspaceit.microblog.service.implementation;

import com.workspaceit.microblog.model.User;
import com.workspaceit.microblog.repository.UserRepository;
import com.workspaceit.microblog.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean emailExist(String email) {
        return userRepository.existsUserByEmail(email);
    }

    @Override
    public void createUser(User user) {
        if (emailExist(user.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        userRepository.save(user);
    }

    @Override
    public User findUser(int id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public User findUser(String email) {
        return userRepository.findUserByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public List<User> findAllUser() {
        return userRepository.findAll();
    }

}
