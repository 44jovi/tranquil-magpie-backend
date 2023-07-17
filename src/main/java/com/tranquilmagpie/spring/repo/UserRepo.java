package com.tranquilmagpie.spring.repo;

import com.tranquilmagpie.spring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepo extends JpaRepository<User, UUID> {
    // JpaRepository provides standard CRUD functions
    // Custom methods:

    @Override
    Optional<User> findById(UUID id);

    Optional<User> findByUsername(String username);
}
