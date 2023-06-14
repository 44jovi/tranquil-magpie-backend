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


}
