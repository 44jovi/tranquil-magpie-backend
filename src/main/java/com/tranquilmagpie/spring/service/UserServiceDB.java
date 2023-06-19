package com.tranquilmagpie.spring.service;

import com.tranquilmagpie.spring.model.User;
import com.tranquilmagpie.spring.repo.UserRepo;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

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
    public User getOneById(UUID id) {
        // TODO: review how to handle empty/null Optional
        // TODO: review usage of isPresent()
        return this.repo.findByUuid(id).get();
    }

    @Override
    public User createOne(User user) {
        return this.repo.save(user);
    }

    @Override
    public User deleteOneById(UUID id) {
        User selectedUser = this.getOneById(id);
        this.repo.deleteByUuid(id);
        return selectedUser;
    }

//    @Override
//    public User patchOneById(UUID id, User user) {
//
//        String email = user.getEmail();
//        String username = user.getUsername();
//        String firstName = user.getFirstName();
//        String lastName = user.getLastName();
//        LocalDate dob = user.getDob();
//
//        User selectedUser = this.getOneById(id);
//
////        TODO: use ternary statements?
//        if (email != null)
//            selectedUser.setEmail(email);
//        if (username != null)
//            selectedUser.setUsername(username);
//        if (firstName != null)
//            selectedUser.setFirstName(firstName);
//        if (lastName != null)
//            selectedUser.setLastName(lastName);
//        if (dob != null)
//            selectedUser.setDob(dob);
//
//        //  .save - JPA calls entityManager.merge if entity already exists (otherwise entityManager.persist)
//        return this.repo.save(selectedUser);
//    }

}
