package com.tranquilmagpie.spring.service.impl;

import com.tranquilmagpie.spring.model.ShopOrder;
import com.tranquilmagpie.spring.model.ShopOrderStatus;
import com.tranquilmagpie.spring.model.UserAddress;
import com.tranquilmagpie.spring.repo.ShopOrderRepo;
import com.tranquilmagpie.spring.repo.UserAddressRepo;
import com.tranquilmagpie.spring.service.ShopOrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class ShopOrderServiceImpl implements ShopOrderService {

    private final ShopOrderRepo shopOrderRepo;
    private final UserAddressRepo userAddressRepo;

    public ShopOrderServiceImpl(ShopOrderRepo shopOrderRepo, UserAddressRepo userAddressRepo) {
        super();
        this.shopOrderRepo = shopOrderRepo;
        this.userAddressRepo = userAddressRepo;
    }

    // TODO: for ADMIN role only
    @Override
    public List<ShopOrder> getAll() {
        return this.shopOrderRepo.findAll();
    }

    // TODO: only allow access to current user's orders
    @Override
    public ShopOrder getById(UUID id) {
        // TODO: handle empty Optional
        return this.shopOrderRepo.findById(id).get();
    }

    @Override
    public List<ShopOrder> getAllByUserId(UUID id) {
        // TODO: handle empty Optional
        return this.shopOrderRepo.findAllByUserId(id).get();
    }

    @Override
    public ShopOrder create(ShopOrder shopOrder) {
        UUID userId = shopOrder.getUserId();
        UserAddress userAddress = userAddressRepo.findByUserId(userId).get();

        shopOrder.setShippingAddress(userAddress.toString());
        shopOrder.setOrderDateTime(Instant.now());

        return this.shopOrderRepo.save(shopOrder);
    }

    //  TODO: for ADMIN role only
    @Override
    @Transactional
    public ShopOrder deleteById(UUID id) {
        // TODO: review usage of isPresent()
        ShopOrder selectedShopOrder = this.shopOrderRepo.findById(id).get();
        this.shopOrderRepo.deleteById(id);
        return selectedShopOrder;
    }

    // TODO: for ADMIN role only?
    @Override
    public ShopOrder patchById(UUID id, ShopOrder shopOrder) {

        UUID userId = shopOrder.getUserId();
        BigDecimal orderTotal = shopOrder.getOrderTotal();
        ShopOrderStatus shopOrderStatus = shopOrder.getOrderStatus();
        String paymentMethod = shopOrder.getPaymentMethod();
        String shippingAddress = shopOrder.getShippingAddress();

        ShopOrder selectedShopOrder = this.getById(id);

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

        return this.shopOrderRepo.save(selectedShopOrder);
    }

}
