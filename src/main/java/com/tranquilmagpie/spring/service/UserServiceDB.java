package com.tranquilmagpie.spring.service;

import com.tranquilmagpie.spring.model.User;
import com.tranquilmagpie.spring.repo.UsersRepo;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Primary
@Service
public class UserServiceDB implements UserService {
//    private UserRepo repo;

    private UsersRepo repo;

    public UserServiceDB(UsersRepo repo) {
        super();
        this.repo = repo;
    }

    @Override
    public List<User> getAll() {
        return this.repo.findAll();
    }

    @Override
    public User getOneById(Long id) {
        // TODO: review how to handle Optional
        // WIP - return null if entity not found
        return this.repo.findById(id).orElse(null);
    }

    @Override
    public User createOne(User user) {
        return this.repo.save(user);
    }
}
