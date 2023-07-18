package com.tranquilmagpie.spring.service;

import com.tranquilmagpie.spring.model.user.UserAddress;

import java.util.List;
import java.util.UUID;

public interface UserAddressService {
    List<UserAddress> getAll();

    UserAddress getById(UUID id);

    UserAddress getByUserId(UUID id);

    UserAddress create(UserAddress userAddress);

    UserAddress deleteById(UUID id);

    UserAddress patchById(UUID id, UserAddress userAddress);
}
