package com.tranquilmagpie.springboot.service;

import java.util.List;

import com.tranquilmagpie.springboot.domain.User;

public interface UserService {
    List<User> getAll();
    User createUser(User user);
}
