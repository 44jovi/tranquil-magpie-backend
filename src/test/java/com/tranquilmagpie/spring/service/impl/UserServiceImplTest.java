package com.tranquilmagpie.spring.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.tranquilmagpie.spring.model.User;
import com.tranquilmagpie.spring.repo.UserRepo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class UserServiceImplTest {

    @Test
    public void testGetAll() {
        List<User> usersList = new ArrayList<>();
        usersList.add(new User());
        usersList.add(new User());

        UserRepo UserRepoMock = mock(UserRepo.class);
        when(UserRepoMock.findAll()).thenReturn(usersList);

        UserServiceImpl impl = new UserServiceImpl(UserRepoMock);

        List<User> usersFound = impl.getAll();

        assertEquals(User.class, usersFound.get(0).getClass());
        assertEquals(User.class, usersFound.get(1).getClass());
    }

}
