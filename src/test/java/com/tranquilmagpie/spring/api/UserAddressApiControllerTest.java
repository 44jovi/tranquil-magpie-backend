package com.tranquilmagpie.spring.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.tranquilmagpie.spring.model.UserAddress;
import com.tranquilmagpie.spring.service.UserAddressService;
import com.tranquilmagpie.spring.service.impl.UserAddressServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

class UserAddressApiControllerTest {
    List<UserAddress> userAddressesList;
    ResponseEntity<List<UserAddress>> userAddressesListResEnt;
    UserAddress userAddress1;
    ResponseEntity<UserAddress> userAddress1ResEntOK;
    ResponseEntity<UserAddress> userAddress1ResEntCreated;
    UserAddress userAddress2;

    UserAddressService UserAddressServiceImplMock;
    UserAddressApiController controller;
    UUID uuid;

    @BeforeEach
    void setUp() {
        userAddressesList = new ArrayList<>();
        userAddressesListResEnt = new ResponseEntity<>(userAddressesList, HttpStatus.OK);

        userAddress1 = UserAddress.builder()
                .userId(UUID.randomUUID())
                .line_1("1 phoebe street")
                .city("phoebeville")
                .postcode("PHO1E23")
                .build();

        userAddress1ResEntOK = new ResponseEntity<>(userAddress1, HttpStatus.OK);
        userAddress1ResEntCreated = new ResponseEntity<>(userAddress1, HttpStatus.CREATED);

        userAddress2 = UserAddress.builder()
                .userId(UUID.randomUUID())
                .line_1("1 monica street")
                .city("monicaville")
                .postcode("MON1C23")
                .build();

        userAddressesList.add(userAddress1);
        userAddressesList.add(userAddress2);

        uuid = UUID.fromString("f95bce3c-2146-47b4-b89e-8de113f5379a");

        UserAddressServiceImplMock = mock(UserAddressServiceImpl.class);
        when(UserAddressServiceImplMock.getAll()).thenReturn(userAddressesList);
        when(UserAddressServiceImplMock.getOneById(uuid)).thenReturn(userAddress1);
        when(UserAddressServiceImplMock.getOneByUserId(uuid)).thenReturn(userAddress1);
        when(UserAddressServiceImplMock.createOne(userAddress1)).thenReturn(userAddress1);
        when(UserAddressServiceImplMock.deleteOneById(uuid)).thenReturn(userAddress1);
        when(UserAddressServiceImplMock.patchOneById(uuid, userAddress1)).thenReturn(userAddress1);

        controller = new UserAddressApiController(UserAddressServiceImplMock);
    }

    @Test
    void testGetAll() {
        ResponseEntity<List<UserAddress>> foundUsers = controller.getAll();

        assertEquals(userAddressesListResEnt, foundUsers);
        verify(UserAddressServiceImplMock, times(1)).getAll();
    }

    @Test
    void testGetOneById() {
        ResponseEntity<UserAddress> foundUserAddress = controller.getOneById(uuid);

        assertEquals(userAddress1ResEntOK, foundUserAddress);
        verify(UserAddressServiceImplMock, times(1)).getOneById(uuid);
    }

    @Test
    void testGetOneByUserId() {
        ResponseEntity<UserAddress> foundUserAddress = controller.getOneByUserId(uuid);

        assertEquals(userAddress1ResEntOK, foundUserAddress);
        verify(UserAddressServiceImplMock, times(1)).getOneByUserId(uuid);
    }

    @Test
    void testCreateOne() {
        ResponseEntity<UserAddress> createdUserAddress = controller.createOne(userAddress1);

        assertEquals(userAddress1ResEntCreated, createdUserAddress);
        verify(UserAddressServiceImplMock, times(1)).createOne(userAddress1);
    }

    @Test
    void testDeleteOneById() {
        ResponseEntity<UserAddress> deletedUserAddress = controller.deleteOneById(uuid);

        assertEquals(userAddress1ResEntOK, deletedUserAddress);
        verify(UserAddressServiceImplMock, times(1)).deleteOneById(uuid);
    }

    @Test
    void testPatchOneById() {
        ResponseEntity<UserAddress> patchedUserAddress = controller.patchOneById(uuid, userAddress1);

        assertEquals(userAddress1ResEntOK, patchedUserAddress);
        verify(UserAddressServiceImplMock, times(1)).patchOneById(uuid, userAddress1);
    }
}
