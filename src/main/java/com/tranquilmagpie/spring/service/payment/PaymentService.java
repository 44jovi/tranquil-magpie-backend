package com.tranquilmagpie.spring.service.payment;

import com.stripe.exception.StripeException;

import java.util.UUID;

public interface PaymentService {

    String createCheckoutSessionByShopOrderId(UUID shopOrderID) throws StripeException;

}
