package com.tranquilmagpie.spring.service.impl;

import com.tranquilmagpie.spring.model.shoporder.ShopOrder;
import com.tranquilmagpie.spring.model.user.UserAddress;
import com.tranquilmagpie.spring.repo.shoporder.ShopOrderRepo;
import com.tranquilmagpie.spring.repo.user.UserAddressRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;

class ShopOrderServiceImplTest {
    List<ShopOrder> shopOrdersList;
    ShopOrder shopOrder1;
    ShopOrderRepo ShopOrderRepoMock;
    UserAddressRepo UserAddressRepoMock;
    ShopOrderServiceImpl shopOrderServiceImpl;

    @BeforeEach
    void setUp() {
        shopOrdersList = new ArrayList<>();

        shopOrder1 = ShopOrder.builder().userId(UUID.randomUUID()).build();

        shopOrdersList.add(shopOrder1);
        shopOrdersList.add(new ShopOrder());

        ShopOrderRepoMock = mock(ShopOrderRepo.class);
        UserAddressRepoMock = mock(UserAddressRepo.class);

        when(ShopOrderRepoMock.findAll()).thenReturn(shopOrdersList);
        when(ShopOrderRepoMock.findById(any(UUID.class))).thenReturn(Optional.ofNullable(new ShopOrder()));
        when(ShopOrderRepoMock.findAllByUserId(any(UUID.class))).thenReturn(Optional.ofNullable(shopOrdersList));
        when(ShopOrderRepoMock.save(shopOrder1)).thenReturn(shopOrder1);
        when(UserAddressRepoMock.findByUserId(any(UUID.class))).thenReturn(Optional.ofNullable(new UserAddress()));

        shopOrderServiceImpl = new ShopOrderServiceImpl(ShopOrderRepoMock, UserAddressRepoMock);
    }

    @Test
    void testGetAll() {
        shopOrderServiceImpl.getAll();

        verify(ShopOrderRepoMock, times(1)).findAll();
    }

    @Test
    void testGetOneById() {
        shopOrderServiceImpl.getById(UUID.randomUUID());

        verify(ShopOrderRepoMock, times(1)).findById(any(UUID.class));
    }

    @Test
    void testGetAllByUserId() {
        shopOrderServiceImpl.getAllByUserId(UUID.randomUUID());

        verify(ShopOrderRepoMock, times(1)).findAllByUserId(any(UUID.class));
    }

    @Test
    void testCreateOne() {
        shopOrderServiceImpl.create(shopOrder1);

        verify(ShopOrderRepoMock, times(1)).save(any(ShopOrder.class));
    }

    @Test
    void testDeleteOneById() {
        shopOrderServiceImpl.deleteById(UUID.randomUUID());

        verify(ShopOrderRepoMock, times(1)).findById(any(UUID.class));
        verify(ShopOrderRepoMock, times(1)).deleteById(any(UUID.class));
    }

    @Test
    void testPatchOneById() {
        // TODO: test the method's conditional logic
        shopOrderServiceImpl.patchById(UUID.randomUUID(), shopOrder1);

        verify(ShopOrderRepoMock, times(1)).save(any(ShopOrder.class));
    }

}
