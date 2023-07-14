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
    ShopOrderRepo ShopOrderRepoMock;
    UserAddressRepo UserAddressRepoMock;
    ShopOrderServiceImpl shopOrderServiceImpl;
    private final BigDecimal orderTotal1 = BigDecimal.valueOf(Double.parseDouble("0.00"));

    @BeforeEach
    void setUp() {
        shopOrdersList = new ArrayList<>();

        shopOrder1 = ShopOrder.builder()
                .userId(UUID.randomUUID())
                .orderDateTime(Instant.now())
                .orderTotal(orderTotal1)
                .orderStatus(PENDING)
                .paymentMethod("")
                .shippingAddress("")
                .build();

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
