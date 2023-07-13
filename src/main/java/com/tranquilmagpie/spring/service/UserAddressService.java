package com.tranquilmagpie.spring.service;

import com.tranquilmagpie.spring.model.UserAddress;

import java.util.List;
import java.util.UUID;

public interface UserAddressService {
    List<UserAddress> getAll();

    UserAddress getOneById(UUID id);

    UserAddress getOneByUserId(UUID id);

    UserAddress createOne(UserAddress userAddress);

    UserAddress deleteOneById(UUID id);

    UserAddress patchOneById(UUID id, UserAddress userAddress);
}
