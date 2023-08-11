package com.tranquilmagpie.spring.service.payment;

import com.stripe.exception.StripeException;
import com.tranquilmagpie.spring.api.payment.CheckoutSessionResponse;

import java.util.UUID;

public interface PaymentService {

    CheckoutSessionResponse createCheckoutSessionByShopOrderId(UUID shopOrderID) throws StripeException;

}
