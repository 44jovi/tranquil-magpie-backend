package com.tranquilmagpie.spring.api;

import com.tranquilmagpie.spring.model.shoporder.ShopOrder;
import com.tranquilmagpie.spring.service.shoporder.ShopOrderService;
import com.tranquilmagpie.spring.service.impl.shoporder.ShopOrderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ShopOrderApiControllerTest {
    List<ShopOrder> shopOrdersList;
    ResponseEntity<List<ShopOrder>> shopOrdersListResEnt;
    ShopOrder shopOrder1;
    ResponseEntity<ShopOrder> shopOrder1ResEntOK;
    ResponseEntity<ShopOrder> shopOrder1ResEntCreated;
    ShopOrderService ShopOrderServiceImplMock;
    ShopOrderApiController controller;
    UUID uuid;

    @BeforeEach
    void setUp() {
        shopOrdersList = new ArrayList<>();
        shopOrdersListResEnt = new ResponseEntity<>(shopOrdersList, HttpStatus.OK);

        shopOrder1 = ShopOrder.builder().userId(UUID.randomUUID()).build();

        shopOrder1ResEntOK = new ResponseEntity<>(shopOrder1, HttpStatus.OK);
        shopOrder1ResEntCreated = new ResponseEntity<>(shopOrder1, HttpStatus.CREATED);

        shopOrdersList.add(shopOrder1);
        shopOrdersList.add(new ShopOrder());

        uuid = UUID.randomUUID();

        ShopOrderServiceImplMock = mock(ShopOrderServiceImpl.class);
        when(ShopOrderServiceImplMock.getAll()).thenReturn(shopOrdersList);
        when(ShopOrderServiceImplMock.getById(uuid)).thenReturn(shopOrder1);
        when(ShopOrderServiceImplMock.getAllByUserId(uuid)).thenReturn(shopOrdersList);
        when(ShopOrderServiceImplMock.create(shopOrder1)).thenReturn(shopOrder1);
        when(ShopOrderServiceImplMock.deleteById(uuid)).thenReturn(shopOrder1);
        when(ShopOrderServiceImplMock.patchById(uuid, shopOrder1)).thenReturn(shopOrder1);

        controller = new ShopOrderApiController(ShopOrderServiceImplMock);
    }

    @Test
    void testGetAll() {
        ResponseEntity<List<ShopOrder>> foundUsers = controller.getAll();

        assertEquals(shopOrdersListResEnt, foundUsers);
        verify(ShopOrderServiceImplMock, times(1)).getAll();
    }

    @Test
    void testGetOneById() {
        ResponseEntity<ShopOrder> foundShopOrder = controller.getById(uuid);

        assertEquals(shopOrder1ResEntOK, foundShopOrder);
        verify(ShopOrderServiceImplMock, times(1)).getById(uuid);
    }

    @Test
    void testGetAllByUserId() {
        ResponseEntity<List<ShopOrder>> foundShopOrder = controller.getAllByUserId(uuid);

        assertEquals(shopOrdersListResEnt, foundShopOrder);
        verify(ShopOrderServiceImplMock, times(1)).getAllByUserId(uuid);
    }

    @Test
    void testCreateOne() {
        ResponseEntity<ShopOrder> createdShopOrder = controller.create(shopOrder1);

        assertEquals(shopOrder1ResEntCreated, createdShopOrder);
        verify(ShopOrderServiceImplMock, times(1)).create(shopOrder1);
    }

    @Test
    void testDeleteOneById() {
        ResponseEntity<ShopOrder> deletedShopOrder = controller.deleteById(uuid);

        assertEquals(shopOrder1ResEntOK, deletedShopOrder);
        verify(ShopOrderServiceImplMock, times(1)).deleteById(uuid);
    }

    @Test
    void testPatchOneById() {
        ResponseEntity<ShopOrder> patchedShopOrder = controller.patchById(uuid, shopOrder1);

        assertEquals(shopOrder1ResEntOK, patchedShopOrder);
        verify(ShopOrderServiceImplMock, times(1)).patchById(uuid, shopOrder1);
    }
}
