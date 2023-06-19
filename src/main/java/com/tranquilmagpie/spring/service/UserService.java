package com.tranquilmagpie.spring.service;

import com.tranquilmagpie.spring.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    List<User> getAll();

    User getOneById(UUID id);

    User createOne(User user);

    User deleteOneById(UUID id);

//    User patchOneById(UUID id, User user);
}
