package com.project3.userservice.service;

import com.project3.userservice.dto.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    List<User> getUsers();
    User getUserById(String userId);
}
