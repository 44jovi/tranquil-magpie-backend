package com.tranquilmagpie.spring.service;

import com.tranquilmagpie.spring.model.ShopOrder;

import java.util.List;
import java.util.UUID;

public interface ShopOrderService {
    List<ShopOrder> getAll();

    ShopOrder getOneById(UUID id);

    List<ShopOrder> getAllByUserId(UUID id);

    ShopOrder createOne(ShopOrder shopOrder);

    ShopOrder deleteOneById(UUID id);

    ShopOrder patchOneById(UUID id, ShopOrder shopOrder);
}
