package com.tranquilmagpie.user.service;

import com.tranquilmagpie.user.model.User;
import com.tranquilmagpie.user.repo.UsersRepo;
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
