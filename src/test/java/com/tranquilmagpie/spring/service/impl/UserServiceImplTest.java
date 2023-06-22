package com.tranquilmagpie.spring.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.tranquilmagpie.spring.model.User;
import com.tranquilmagpie.spring.repo.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

class UserServiceImplTest {
    List<User> usersList;
    User user1;
    User user2;
    User user3;
    UserRepo UserRepoMock;

    UserServiceImpl userServiceImpl;

    @BeforeEach
    public void testSetup() {
        usersList = new ArrayList<>();
        user1 = new User("phoebe1@test.com", "phoebe1", "phoebe", "buffay", LocalDate.parse("1966-02-16"));
        user2 = new User("monica1@test.com", "monica1", "monica", "geller", LocalDate.parse("1969-01-01"));
        user3 = new User("rachel1@test.com", "rachel1", "rachel", "green", LocalDate.parse("1969-05-05"));
        usersList.add(user1);
        usersList.add(user2);

        UserRepoMock = mock(UserRepo.class);
        when(UserRepoMock.findAll()).thenReturn(usersList);
        when(UserRepoMock.findByUuid(any(UUID.class))).thenReturn(Optional.ofNullable(user1));
        when(UserRepoMock.save(user3)).thenReturn(user3);

        userServiceImpl = new UserServiceImpl(UserRepoMock);
    }

    @Test
    public void testGetAll() {
        List<User> usersFound = userServiceImpl.getAll();

        assertEquals(User.class, usersFound.get(0).getClass());
        assertEquals("phoebe1", usersFound.get(0).getUsername());
        assertEquals(User.class, usersFound.get(1).getClass());
        assertEquals("monica1", usersFound.get(1).getUsername());
    }

    @Test
    public void testGetOneById() {
        User userFound = userServiceImpl.getOneById(UUID.randomUUID());

        assertEquals(User.class, userFound.getClass());
        assertEquals("phoebe1", userFound.getUsername());
    }

    @Test
    public void createOne(){
        User userCreated = userServiceImpl.createOne(user3);
        assertEquals(User.class, userCreated.getClass());
        assertEquals("rachel1", userCreated.getUsername());
    }

}
