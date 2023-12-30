package com.tranquilmagpie.spring.service.user;

import com.tranquilmagpie.spring.model.user.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    List<User> getAll();

    User getById(UUID id);

    User getByUsername(String username);

    User create(User user);

    User deleteById(UUID id);

    User patchById(UUID id, User user);
}
