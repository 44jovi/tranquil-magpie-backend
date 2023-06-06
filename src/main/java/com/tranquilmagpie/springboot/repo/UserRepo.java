package com.tranquilmagpie.springboot.repo;

import com.tranquilmagpie.springboot.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
  // JpaRepository provides standard CRUD functions
  // Insert custom methods here
}
