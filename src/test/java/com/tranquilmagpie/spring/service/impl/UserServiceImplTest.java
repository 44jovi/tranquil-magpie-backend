package com.tranquilmagpie.spring.service.impl;

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
    UserRepo UserRepoMock;
    UserServiceImpl userServiceImpl;

    @BeforeEach
    void setUp() {
        usersList = new ArrayList<>();
        user1 = new User("phoebe1@test.com", "phoebe1", "phoebe", "buffay", LocalDate.parse("1966-02-16"), "pass123");
        user2 = new User("monica1@test.com", "monica1", "monica", "geller", LocalDate.parse("1969-01-01"), "pass123");
        usersList.add(user1);
        usersList.add(user2);

        UserRepoMock = mock(UserRepo.class);
        when(UserRepoMock.findAll()).thenReturn(usersList);
        when(UserRepoMock.findById(any(UUID.class))).thenReturn(Optional.ofNullable(user1));
        when(UserRepoMock.save(user1)).thenReturn(user1);

        userServiceImpl = new UserServiceImpl(UserRepoMock);
    }

    @Test
    void testGetAll() {
        userServiceImpl.getAll();

        verify(UserRepoMock, times(1)).findAll();
    }

    @Test
    void testGetOneById() {
        userServiceImpl.getOneById(UUID.randomUUID());

        verify(UserRepoMock, times(1)).findById(any(UUID.class));
    }

    @Test
    void testCreateOne() {
        userServiceImpl.createOne(user1);

        verify(UserRepoMock, times(1)).save(any(User.class));
    }

    @Test
    void testDeleteOneById() {
        userServiceImpl.deleteOneById(UUID.randomUUID());

        verify(UserRepoMock, times(1)).findById(any(UUID.class));
        verify(UserRepoMock, times(1)).deleteById(any(UUID.class));
    }

    @Test
    void testPatchOneById() {
        // TODO: test the method's conditional logic
        userServiceImpl.patchOneById(UUID.randomUUID(), user1);

        verify(UserRepoMock, times(1)).save(any(User.class));
    }

}
