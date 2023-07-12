package com.tranquilmagpie.spring.repo;

import com.tranquilmagpie.spring.model.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserAddressRepo extends JpaRepository<UserAddress, Integer> {

    Optional<UserAddress> findByUserId(UUID id);

}
