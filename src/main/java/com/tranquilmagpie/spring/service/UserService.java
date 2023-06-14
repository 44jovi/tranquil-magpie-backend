package com.tranquilmagpie.spring.service;

import com.tranquilmagpie.spring.model.User;

import java.util.List;

public interface UserService {
    List<User> getAll();

    User getOneById(Long id);

    //TODO: remaining standard DB operations
}
