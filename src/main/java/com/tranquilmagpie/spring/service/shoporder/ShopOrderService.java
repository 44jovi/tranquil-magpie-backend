package com.tranquilmagpie.spring.service.shoporder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tranquilmagpie.spring.model.shoporder.ShopOrder;

import java.util.List;
import java.util.UUID;

public interface ShopOrderService {
    List<ShopOrder> getAll();

    ShopOrder getById(UUID id);

    List<ShopOrder> getAllByUserId(UUID id);

    ShopOrder create(ShopOrder shopOrder) throws JsonProcessingException;

    ShopOrder deleteById(UUID id);

    ShopOrder patchById(UUID id, ShopOrder shopOrder);

    ShopOrder confirm(UUID id);

    ShopOrder cancel(UUID id);

    ShopOrder updateStripeCheckoutSessionId(UUID id, String stripeCheckoutSessionId);

    ShopOrder updateStripePaymentIntentId(UUID id, String stripePaymentIntentId);

    ShopOrder confirmPayment(UUID id, String paymentMethod);

}
