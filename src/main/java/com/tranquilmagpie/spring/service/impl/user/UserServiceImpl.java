package com.tranquilmagpie.spring.service.impl.user;

import com.tranquilmagpie.spring.model.user.User;
import com.tranquilmagpie.spring.repo.user.UserRepo;
import com.tranquilmagpie.spring.service.user.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo repo;

    public UserServiceImpl(UserRepo repo) {
        super();
        this.repo = repo;
    }

    // TODO: for ADMIN role only
    @Override
    public List<User> getAll() {
        return this.repo.findAll();
    }

    // TODO: only allow access to current user's details
    @Override
    public User getById(UUID id) {
        // TODO: review usage of isPresent()
        return this.repo.findById(id).get();
    }

    @Override
    public User create(User user) {
        // TODO: do not allow username to be 'admin'
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
        String passwordString = user.getPassword();
        String passwordHashed = encoder.encode((passwordString));
        user.setPassword(passwordHashed);

        return this.repo.save(user);
    }

    // TODO: for ADMIN role only
    @Override
    // Manage multiple database calls
    @Transactional
    public User deleteById(UUID id) {
        // TODO: review usage of isPresent()
        User selectedUser = this.repo.findById(id).get();
        this.repo.deleteById(id);
        return selectedUser;
    }

    @Override
    public User patchById(UUID id, User user) {

        String email = user.getEmail();
        String username = user.getUsername();
        String firstName = user.getGivenName();
        String lastName = user.getFamilyName();
        LocalDate dob = user.getDob();

        User selectedUser = this.getById(id);

//        TODO: use ternary statements?
        if (email != null)
            selectedUser.setEmail(email);
        if (username != null)
            selectedUser.setUsername(username);
        if (firstName != null)
            selectedUser.setGivenName(firstName);
        if (lastName != null)
            selectedUser.setFamilyName(lastName);
        if (dob != null)
            selectedUser.setDob(dob);

        //  .save - JPA calls entityManager.merge if entity already exists (otherwise entityManager.persist)
        return this.repo.save(selectedUser);
    }

}
