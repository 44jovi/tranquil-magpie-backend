package com.tranquilmagpie.spring.api;

import static org.mockito.Mockito.*;

import com.tranquilmagpie.spring.model.User;
import com.tranquilmagpie.spring.service.UserService;
import com.tranquilmagpie.spring.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class UserApiControllerTest {
    List<User> usersList;
    User user1;
    User user2;
    UserService UserServiceImplMock;
    UserApiController controller;

    @BeforeEach
    void testSetup() {
        usersList = new ArrayList<>();
        user1 = new User("phoebe1@test.com", "phoebe1", "phoebe", "buffay", LocalDate.parse("1966-02-16"));
        user2 = new User("monica1@test.com", "monica1", "monica", "geller", LocalDate.parse("1969-01-01"));
        usersList.add(user1);
        usersList.add(user2);

        UserServiceImplMock = mock(UserServiceImpl.class);
        when(UserServiceImplMock.getAll()).thenReturn(usersList);

        controller = new UserApiController(UserServiceImplMock);
    }

    @Test
    void testGetAll(){
        controller.getAll();

        verify(UserServiceImplMock, times(1)).getAll();
    }

//    @Test
//    void getOneById(){}
//    @Test
//    void createOne(){}
//    @Test
//    void deleteOneById(){}
//    @Test
//    void updateOneById(){}
}
