package com.gla.demo.core.service.impl;

import com.gla.demo.core.UserGateway;
import com.gla.demo.core.model.User;
import com.gla.demo.core.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserGateway userGateway;

    @Override
    public Optional<List<User>> getAllUsers() {

        Optional<List<User>> userList = userGateway.getAllUsers();

        return userList.isPresent() && !userList.get().isEmpty() ? userList : Optional.empty();
    }

    @Override
    public Optional<User> getUserById(Long id) {

        Optional<User> user = userGateway.getUserById(id);

        return user.isPresent() ? user : Optional.empty();
    }

    @Override
    public void addUser(User user) {

        userGateway.addUser(user);
    }

    @Override
    public void updateUser(User user, Long id) {

        userGateway.updateUser(user, id);
    }

    @Override
    public void deleteUserById(Long id) {

        userGateway.deleteUserById(id);
    }
}
