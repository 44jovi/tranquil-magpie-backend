package com.tranquilmagpie.spring.api;

import com.tranquilmagpie.spring.model.User;
import com.tranquilmagpie.spring.service.UserService;
import com.tranquilmagpie.spring.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserApiControllerTest {
    List<User> usersList;
    ResponseEntity<List<User>> usersListResEnt;
    User user1;
    ResponseEntity<User> user1ResEntOK;
    ResponseEntity<User> user1ResEntCreated;
    UserService UserServiceImplMock;
    UserApiController controller;
    UUID uuid;

    @BeforeEach
    void setUp() {
        usersList = new ArrayList<>();
        usersListResEnt = new ResponseEntity<>(usersList, HttpStatus.OK);

        user1 = new User();

        user1ResEntOK = new ResponseEntity<>(user1, HttpStatus.OK);
        user1ResEntCreated = new ResponseEntity<>(user1, HttpStatus.CREATED);

        usersList.add(user1);
        usersList.add(new User());

        uuid = UUID.randomUUID();

        UserServiceImplMock = mock(UserServiceImpl.class);
        when(UserServiceImplMock.getAll()).thenReturn(usersList);
        when(UserServiceImplMock.getById(uuid)).thenReturn(user1);
        when(UserServiceImplMock.create(user1)).thenReturn(user1);
        when(UserServiceImplMock.deleteById(uuid)).thenReturn(user1);
        when(UserServiceImplMock.patchById(uuid, user1)).thenReturn(user1);

        controller = new UserApiController(UserServiceImplMock);
    }

    @Test
    void testGetAll(){
        ResponseEntity<List<User>> foundUsers = controller.getAll();

        assertEquals(usersListResEnt, foundUsers);
        verify(UserServiceImplMock, times(1)).getAll();
    }

    @Test
    void testGetOneById(){
        ResponseEntity<User> foundUser = controller.getById(uuid);

        assertEquals(user1ResEntOK, foundUser);
        verify(UserServiceImplMock, times(1)).getById(uuid);
    }
    @Test
    void testCreateOne(){
        ResponseEntity<User> createdUser = controller.create(user1);

        assertEquals(user1ResEntCreated, createdUser);
        verify(UserServiceImplMock, times(1)).create(user1);
    }
    @Test
    void testDeleteOneById(){
        ResponseEntity<User> deletedUser = controller.deleteById(uuid);

        assertEquals(user1ResEntOK, deletedUser);
        verify(UserServiceImplMock, times(1)).deleteById(uuid);
    }

    @Test
    void testPatchOneById() {
        ResponseEntity<User> patchedUser = controller.patchById(uuid, user1);

        assertEquals(user1ResEntOK, patchedUser);
        verify(UserServiceImplMock, times(1)).patchById(uuid, user1);
    }
}
