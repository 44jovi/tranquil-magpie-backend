package com.tranquilmagpie.spring.service.impl;

import com.tranquilmagpie.spring.model.UserAddress;
import com.tranquilmagpie.spring.repo.UserAddressRepo;
import com.tranquilmagpie.spring.service.UserAddressService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class UserAddressServiceImpl implements UserAddressService {

    private UserAddressRepo repo;

    public UserAddressServiceImpl(UserAddressRepo repo) {
        super();
        this.repo = repo;
    }

    // TODO: for ADMIN role only
    @Override
    public List<UserAddress> getAll() {
        return this.repo.findAll();
    }

    // TODO: restrict this to current authenticated/authorised user
    @Override
    public UserAddress getOneById(UUID id) {
        // TODO: handle empty Optional
        return this.repo.findById(id).get();
    }

    // TODO: only allow access to current user's address
    @Override
    public UserAddress getOneByUserId(UUID id) {
        // TODO: handle empty Optional
        return this.repo.findByUserId(id).get();
    }

    @Override
    public UserAddress createOne(UserAddress userAddress) {
        return this.repo.save(userAddress);
    }

    @Override
    @Transactional
    public UserAddress deleteOneById(UUID id) {
        // TODO: review usage of isPresent()
        UserAddress selectedUserAddress = this.repo.findById(id).get();
        this.repo.deleteById(id);
        return selectedUserAddress;
    }

    @Override
    public UserAddress patchOneById(UUID id, UserAddress user) {

        UUID userId = user.getUserId();
        String addressLine1 = user.getLine_1();
        String city = user.getCity();
        String postcode = user.getPostcode();

        UserAddress selectedUserAddress = this.getOneById(id);

        if (userId != null)
            selectedUserAddress.setUserId(userId);
        if (addressLine1 != null)
            selectedUserAddress.setLine_1(addressLine1);
        if (city != null)
            selectedUserAddress.setCity(city);
        if (postcode != null)
            selectedUserAddress.setPostcode(postcode);

        return this.repo.save(selectedUserAddress);
    }

}