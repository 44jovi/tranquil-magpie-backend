package com.tranquilmagpie.spring.service.impl;

import com.tranquilmagpie.spring.model.ShopOrder;
import com.tranquilmagpie.spring.model.UserAddress;
import com.tranquilmagpie.spring.repo.ShopOrderRepo;
import com.tranquilmagpie.spring.repo.UserAddressRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.tranquilmagpie.spring.model.ShopOrderStatus.PENDING;
import static org.mockito.Mockito.*;

class ShopOrderServiceImplTest {
    List<ShopOrder> shopOrdersList;
    ShopOrder shopOrder1;
    ShopOrder shopOrder2;
    ShopOrderRepo ShopOrderRepoMock;
    UserAddressRepo UserAddressRepoMock;
    ShopOrderServiceImpl shopOrderServiceImpl;
    private BigDecimal orderTotal1 = BigDecimal.valueOf(Double.parseDouble("12.34"));
    private BigDecimal orderTotal2 = BigDecimal.valueOf(Double.parseDouble("56.78"));


    @BeforeEach
    void setUp() {
        shopOrdersList = new ArrayList<>();

        shopOrder1 = ShopOrder.builder()
                .userId(UUID.randomUUID())
                .orderDateTime(Instant.now())
                .orderTotal(orderTotal1)
                .orderStatus(PENDING)
                .paymentMethod("credit card")
                .shippingAddress("example address 1")
                .build();

        shopOrder2 = ShopOrder.builder()
                .userId(UUID.randomUUID())
                .orderDateTime(Instant.now())
                .orderTotal(orderTotal2)
                .orderStatus(PENDING)
                .paymentMethod("credit card")
                .shippingAddress("example address 2")
                .build();

        shopOrdersList.add(shopOrder1);
        shopOrdersList.add(shopOrder2);

        ShopOrderRepoMock = mock(ShopOrderRepo.class);
        UserAddressRepoMock = mock(UserAddressRepo.class);

//        todo: check all methods are used
        when(ShopOrderRepoMock.findAll()).thenReturn(shopOrdersList);
        when(ShopOrderRepoMock.findById(any(UUID.class))).thenReturn(Optional.ofNullable(shopOrder1));
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
        shopOrderServiceImpl.getOneById(UUID.randomUUID());

        verify(ShopOrderRepoMock, times(1)).findById(any(UUID.class));
    }

    @Test
    void testGetAllByUserId() {
        shopOrderServiceImpl.getAllByUserId(UUID.randomUUID());

        verify(ShopOrderRepoMock, times(1)).findAllByUserId(any(UUID.class));
    }

    @Test
    void testCreateOne() {
        shopOrderServiceImpl.createOne(shopOrder1);

        verify(ShopOrderRepoMock, times(1)).save(any(ShopOrder.class));
    }

    @Test
    void testDeleteOneById() {
        shopOrderServiceImpl.deleteOneById(UUID.randomUUID());

        verify(ShopOrderRepoMock, times(1)).findById(any(UUID.class));
        verify(ShopOrderRepoMock, times(1)).deleteById(any(UUID.class));
    }

    @Test
    void testPatchOneById() {
        // TODO: test the method's conditional logic
        shopOrderServiceImpl.patchOneById(UUID.randomUUID(), shopOrder1);

        verify(ShopOrderRepoMock, times(1)).save(any(ShopOrder.class));
    }

}
