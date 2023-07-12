package com.tranquilmagpie.spring.service;

import com.tranquilmagpie.spring.model.UserAddress;

import java.util.List;
import java.util.UUID;

public interface UserAddressService {
    List<UserAddress> getAll();

    UserAddress getOneById(int id);

    UserAddress getOneByUserId(UUID id);

    UserAddress createOne(UserAddress userAddress);

    UserAddress deleteOneById(int id);

    UserAddress patchOneById(int id, UserAddress userAddress);
}
