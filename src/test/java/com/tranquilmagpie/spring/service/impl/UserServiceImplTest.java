package com.tranquilmagpie.spring.service.impl;

import com.tranquilmagpie.spring.model.user.User;
import com.tranquilmagpie.spring.repo.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;

class UserServiceImplTest {
    List<User> usersList;
    User user1;
    UserRepo UserRepoMock;
    UserServiceImpl userServiceImpl;

    @BeforeEach
    void setUp() {
        usersList = new ArrayList<>();
        user1 = User.builder().password("").build();
        usersList.add(user1);
        usersList.add(new User());

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
        userServiceImpl.getById(UUID.randomUUID());

        verify(UserRepoMock, times(1)).findById(any(UUID.class));
    }

    @Test
    void testCreateOne() {
        userServiceImpl.create(user1);

        verify(UserRepoMock, times(1)).save(any(User.class));
    }

    @Test
    void testDeleteOneById() {
        userServiceImpl.deleteById(UUID.randomUUID());

        verify(UserRepoMock, times(1)).findById(any(UUID.class));
        verify(UserRepoMock, times(1)).deleteById(any(UUID.class));
    }

    @Test
    void testPatchOneById() {
        // TODO: test the method's conditional logic
        userServiceImpl.patchById(UUID.randomUUID(), user1);

        verify(UserRepoMock, times(1)).save(any(User.class));
    }

}
