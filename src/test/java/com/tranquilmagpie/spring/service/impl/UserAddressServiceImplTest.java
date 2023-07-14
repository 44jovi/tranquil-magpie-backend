package com.tranquilmagpie.spring.service.impl;

import com.tranquilmagpie.spring.model.UserAddress;
import com.tranquilmagpie.spring.repo.UserAddressRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;

class UserAddressServiceImplTest {
    List<UserAddress> userAddresssesList;
    UserAddress userAddress1;
    UserAddressRepo UserAddressRepoMock;
    UserAddressServiceImpl userAddressServiceImpl;

    @BeforeEach
    void setUp() {
        userAddresssesList = new ArrayList<>();
        userAddress1 = new UserAddress();
        userAddresssesList.add(userAddress1);
        userAddresssesList.add(new UserAddress());

        UserAddressRepoMock = mock(UserAddressRepo.class);
        when(UserAddressRepoMock.findAll()).thenReturn(userAddresssesList);
        when(UserAddressRepoMock.findById(any(UUID.class))).thenReturn(Optional.ofNullable(userAddress1));
        when(UserAddressRepoMock.findByUserId(any(UUID.class))).thenReturn(Optional.ofNullable(userAddress1));
        when(UserAddressRepoMock.save(userAddress1)).thenReturn(userAddress1);

        userAddressServiceImpl = new UserAddressServiceImpl(UserAddressRepoMock);
    }

    @Test
    void testGetAll() {
        userAddressServiceImpl.getAll();

        verify(UserAddressRepoMock, times(1)).findAll();
    }

    @Test
    void testGetOneById() {
        userAddressServiceImpl.getOneById(UUID.randomUUID());

        verify(UserAddressRepoMock, times(1)).findById(any(UUID.class));
    }

    @Test
    void testGetOneByUserId() {
        userAddressServiceImpl.getOneByUserId(UUID.randomUUID());

        verify(UserAddressRepoMock, times(1)).findByUserId(any(UUID.class));
    }

    @Test
    void testCreateOne() {
        userAddressServiceImpl.createOne(userAddress1);

        verify(UserAddressRepoMock, times(1)).save(any(UserAddress.class));
    }

    @Test
    void testDeleteOneById() {
        userAddressServiceImpl.deleteOneById(UUID.randomUUID());

        verify(UserAddressRepoMock, times(1)).findById(any(UUID.class));
        verify(UserAddressRepoMock, times(1)).deleteById(any(UUID.class));
    }

    @Test
    void testPatchOneById() {
        // TODO: test the method's conditional logic
        userAddressServiceImpl.patchOneById(UUID.randomUUID(), userAddress1);

        verify(UserAddressRepoMock, times(1)).save(any(UserAddress.class));
    }

}
