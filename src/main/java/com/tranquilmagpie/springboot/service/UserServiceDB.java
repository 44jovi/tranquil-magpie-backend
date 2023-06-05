package com.tranquilmagpie.springboot.service;

import com.tranquilmagpie.springboot.domain.User;
import com.tranquilmagpie.springboot.repo.UserRepo;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Primary
@Service
public class UserServiceDB implements UserService {
    private UserRepo repo;

    public UserServiceDB(UserRepo repo) {
        super();
        this.repo = repo;
    }

    @Override
    public List<User> getAll() {
        return this.repo.findAll();
    }

    @Override
    public User createUser(User user) {
        return this.repo.save(user);
    }
    // TODO: /get/id
    // TODO: /remove/id
}
