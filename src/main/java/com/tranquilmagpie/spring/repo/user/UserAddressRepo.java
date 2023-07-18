package com.tranquilmagpie.spring.repo.user;

import com.tranquilmagpie.spring.model.user.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserAddressRepo extends JpaRepository<UserAddress, UUID> {

    @Override
    Optional<UserAddress> findById(UUID id);

    Optional<UserAddress> findByUserId(UUID id);

}
