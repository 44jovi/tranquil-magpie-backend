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

class UserServiceImplTest {
    List<User> usersList;
    User user1;
    User user2;

    @BeforeEach
    public void testSetup() {
        usersList = new ArrayList<>();
        user1 = new User("phoebe1@test.com", "phoebe1", "phoebe", "buffay", LocalDate.parse("1966-02-16"));
        user2 = new User("monica1@test.com", "monica1", "monica", "geller", LocalDate.parse("1969-01-01"));
        usersList.add(user1);
        usersList.add(user2);
    }

    @Test
    public void testGetAll() {
        UserRepo UserRepoMock = mock(UserRepo.class);
        when(UserRepoMock.findAll()).thenReturn(usersList);

        UserServiceImpl impl = new UserServiceImpl(UserRepoMock);

        List<User> usersFound = impl.getAll();

        assertEquals(User.class, usersFound.get(0).getClass());
        assertEquals("phoebe1", usersFound.get(0).getUsername());
        assertEquals(User.class, usersFound.get(1).getClass());
        assertEquals("monica1", usersFound.get(1).getUsername());
    }

}
