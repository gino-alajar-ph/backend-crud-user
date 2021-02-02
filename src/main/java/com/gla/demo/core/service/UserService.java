package com.gla.demo.core.service;

import com.gla.demo.core.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<List<User>> getAllUsers();

    Optional<User> getUserById(Long id);

    void addUser(User user);

    void updateUser(User user, Long id);

    void deleteUserById(Long id);
}
