package com.tranquilmagpie.spring.service;

import com.tranquilmagpie.spring.model.User;

import java.time.LocalDate;
import java.util.List;

public interface UserService {
    List<User> getAll();

    User getOneById(Long id);

    User createOne(User user);

    User deleteOneById(Long id);

    User updateOneById(Long id, String email, String username, String firstName, String lastName, LocalDate dob);

}
