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
    UserAddress userAddress2;
    UserAddressRepo UserAddressRepoMock;
    UserAddressServiceImpl userAddressServiceImpl;
    private final UUID uuid1 = UUID.fromString("9b21e657-7fd9-42ec-b0a9-16eeb4362d1d");
    private final UUID uuid2 = UUID.fromString("3bda1a93-5336-45bd-82be-9ba967b1f3d4");

    @BeforeEach
    void setUp() {
        userAddresssesList = new ArrayList<>();

        userAddress1 = UserAddress.builder()
                .userId(uuid1)
                .line_1("1 phoebe street")
                .city("phoebeville")
                .postcode("PHO1E23")
                .build();

        userAddress2 = UserAddress.builder()
                .userId(uuid2)
                .line_1("1 monica street")
                .city("monicaville")
                .postcode("MON1C23")
                .build();

        userAddresssesList.add(userAddress1);
        userAddresssesList.add(userAddress2);

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
