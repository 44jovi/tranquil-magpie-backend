package com.tranquilmagpie.spring.service;

import com.tranquilmagpie.spring.model.User;
import com.tranquilmagpie.spring.repo.UserRepo;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Primary
@Service
public class UserServiceDB implements UserService {
//    private UserRepo repo;

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
    public User getOneById(Long id) {
        // TODO: review how to handle Optional
        // WIP - return null if entity not found
        return this.repo.findById(id).orElse(null);
    }

    @Override
    public User createOne(User user) {
        return this.repo.save(user);
    }

    @Override
    public User deleteOneById(Long id) {
        User selectedUser = this.getOneById(id);
        this.repo.deleteById(id);
        return selectedUser;
    }

    @Override
    public User updateOneById(Long id, String email, String username, String firstName, String lastName, LocalDate dob) {
        User selectedUser = this.getOneById(id);

//        TODO: use ternary statements?
        if (email != null)
            selectedUser.setEmail(email);
        if (username != null)
            selectedUser.setUsername(username);
        if (firstName != null)
            selectedUser.setFirstName(firstName);
        if (lastName != null)
            selectedUser.setLastName(lastName);
        if (dob != null)
            selectedUser.setDob(dob);

        //  .save - JPA calls entityManager.merge if entity already exists (otherwise entityManager.persist)
        return this.repo.save(selectedUser);
    }

}
