package com.tranquilmagpie.spring.service.impl;

import com.tranquilmagpie.spring.model.ShopOrder;
import com.tranquilmagpie.spring.model.ShopOrderStatus;
import com.tranquilmagpie.spring.repo.ShopOrderRepo;
import com.tranquilmagpie.spring.service.ShopOrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class ShopOrderServiceImpl implements ShopOrderService {

    private ShopOrderRepo repo;

    public ShopOrderServiceImpl(ShopOrderRepo repo) {
        super();
        this.repo = repo;
    }

    // TODO: for ADMIN role only
    @Override
    public List<ShopOrder> getAll() {
        return this.repo.findAll();
    }

    // TODO: only allow access to current user's orders
    @Override
    public ShopOrder getOneById(UUID id) {
        // TODO: handle empty Optional
        return this.repo.findById(id).get();
    }

    @Override
    public ShopOrder getAllByUserId(UUID id) {
        // TODO: handle empty Optional
        return this.repo.findAllByUserId(id).get();
    }

    @Override
    public ShopOrder createOne(ShopOrder shopOrder) {
        shopOrder.setOrderDateTime(Instant.now());
        // todo: read user address and add to order
        return this.repo.save(shopOrder);
    }

    //  TODO: for ADMIN role only
    @Override
    @Transactional
    public ShopOrder deleteOneById(UUID id) {
        // TODO: review usage of isPresent()
        ShopOrder selectedShopOrder = this.repo.findById(id).get();
        this.repo.deleteById(id);
        return selectedShopOrder;
    }

//    TODO: for ADMIN role only?
    @Override
    public ShopOrder patchOneById(UUID id, ShopOrder shopOrder) {

        UUID userId = shopOrder.getUserId();
        BigDecimal orderTotal = shopOrder.getOrderTotal();
        ShopOrderStatus shopOrderStatus = shopOrder.getOrderStatus();
        String paymentMethod = shopOrder.getPaymentMethod();
        String shippingAddress = shopOrder.getShippingAddress();

        ShopOrder selectedShopOrder = this.getOneById(id);

        if (userId != null)
            selectedShopOrder.setUserId(userId);
        if (orderTotal != null)
            selectedShopOrder.setOrderTotal(orderTotal);
        if (shopOrderStatus != null)
            selectedShopOrder.setOrderStatus(shopOrderStatus);
        if (paymentMethod != null)
            selectedShopOrder.setPaymentMethod(paymentMethod);
        if (shippingAddress != null)
            selectedShopOrder.setShippingAddress(shippingAddress);

        shopOrder.setOrderDateTime(Instant.now());

        return this.repo.save(selectedShopOrder);
    }

}
