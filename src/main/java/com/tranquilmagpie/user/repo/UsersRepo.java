package com.tranquilmagpie.user.repo;

import com.tranquilmagpie.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepo extends JpaRepository<User, Long> {
    // JpaRepository provides standard CRUD functions
    // Insert custom methods here
}
