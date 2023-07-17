package com.tranquilmagpie.spring.service;

import com.tranquilmagpie.spring.model.ShopOrder;

import java.util.List;
import java.util.UUID;

public interface ShopOrderService {
    List<ShopOrder> getAll();

    ShopOrder getById(UUID id);

    List<ShopOrder> getAllByUserId(UUID id);

    ShopOrder create(ShopOrder shopOrder);

    ShopOrder deleteById(UUID id);

    ShopOrder patchById(UUID id, ShopOrder shopOrder);
}
