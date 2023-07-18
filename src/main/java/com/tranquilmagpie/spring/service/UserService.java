package com.tranquilmagpie.spring.service;

import com.tranquilmagpie.spring.model.user.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    List<User> getAll();

    User getById(UUID id);

    User create(User user);

    User deleteById(UUID id);

    User patchById(UUID id, User user);
}
