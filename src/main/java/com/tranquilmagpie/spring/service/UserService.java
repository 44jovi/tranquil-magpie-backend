package com.tranquilmagpie.spring.service;

import com.tranquilmagpie.spring.model.User;

import java.util.List;

public interface UserService {
    List<User> getAll();

    User getOneById(Long id);

    User createOne(User user);

    User deleteOneById(Long id);
}
