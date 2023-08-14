package com.tranquilmagpie.spring.service.impl.shoporder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tranquilmagpie.spring.model.shoporder.ShopOrder;
import com.tranquilmagpie.spring.model.shoporder.ShopOrderItem;
import com.tranquilmagpie.spring.model.shoporder.ShopOrderStatus;
import com.tranquilmagpie.spring.model.user.UserAddress;
import com.tranquilmagpie.spring.repo.product.ProductRepo;
import com.tranquilmagpie.spring.repo.shoporder.ShopOrderItemRepo;
import com.tranquilmagpie.spring.repo.shoporder.ShopOrderRepo;
import com.tranquilmagpie.spring.repo.user.UserAddressRepo;
import com.tranquilmagpie.spring.service.shoporder.ShopOrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class ShopOrderServiceImpl implements ShopOrderService {

    private final ShopOrderRepo shopOrderRepo;
    private final ShopOrderItemRepo shopOrderItemRepo;
    private final ProductRepo productRepo;
    private final UserAddressRepo userAddressRepo;


    public ShopOrderServiceImpl(
            ShopOrderRepo shopOrderRepo,
            ShopOrderItemRepo shopOrderItemRepo,
            ProductRepo productRepo,
            UserAddressRepo userAddressRepo
    ) {
        super();
        this.shopOrderRepo = shopOrderRepo;
        this.shopOrderItemRepo = shopOrderItemRepo;
        this.productRepo = productRepo;
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
        return this.shopOrderRepo.findById(id).get();
    }

    @Override
    public List<ShopOrder> getAllByUserId(UUID id) {
        return this.shopOrderRepo.findAllByUserId(id).get();
    }

    @Override
    public ShopOrder create(ShopOrder shopOrder) throws JsonProcessingException {
        UUID userId = shopOrder.getUserId();
        UserAddress userAddress = userAddressRepo.findByUserId(userId).get();

        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(userAddress);

        shopOrder.setShippingAddress(jsonString);
        shopOrder.setOrderDateTime(Instant.now());

        return this.shopOrderRepo.save(shopOrder);
    }

    //  TODO: for ADMIN role only
    @Override
    @Transactional
    public ShopOrder deleteById(UUID id) {
        ShopOrder selectedShopOrder = this.shopOrderRepo.findById(id).get();
        // TODO: for this order:
        //      - add all shop order item qty's back to product stock qty
        //      - delete all shop order items
        this.shopOrderRepo.deleteById(id);
        return selectedShopOrder;
    }

    // TODO: for ADMIN role only
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

    @Override
    public ShopOrder confirm(UUID id) {
        ShopOrder shopOrder = this.shopOrderRepo.findById(id).get();
        shopOrder.setOrderStatus(ShopOrderStatus.CONFIRMED_AWAITING_PAYMENT);
        return this.shopOrderRepo.save(shopOrder);
    }

    @Override
    public ShopOrder cancel(UUID id) {
        ShopOrder shopOrder = this.shopOrderRepo.findById(id).get();

        shopOrder.setOrderStatus(ShopOrderStatus.CANCELLED);

        List<ShopOrderItem> shopOrderItems = shopOrderItemRepo.findByIdShopOrderId(id);

        for (ShopOrderItem item : shopOrderItems) {
            productRepo.addStockQty(item.getId().getProductId(), item.getQty());
        }

        return this.shopOrderRepo.save(shopOrder);
    }

    @Override
    public ShopOrder confirmPayment(UUID id, String paymentMethod) {
        ShopOrder shopOrder = this.shopOrderRepo.findById(id).get();

        if (shopOrder.getOrderStatus() == ShopOrderStatus.CONFIRMED_AWAITING_PAYMENT) {

            shopOrder.setOrderStatus(ShopOrderStatus.PAID_AWAITING_SHIPMENT);
            shopOrder.setPaymentMethod(paymentMethod);

            return this.shopOrderRepo.save(shopOrder);
        } else {
            // TODO: review
            return null;
        }
    }

}
