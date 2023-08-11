package com.tranquilmagpie.spring.api.payment;

import com.stripe.exception.StripeException;
import com.tranquilmagpie.spring.service.payment.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;


@RestController
@RequestMapping("/payment")
public class PaymentApiController {

    private final PaymentService service;

    public PaymentApiController(PaymentService service) {
        super();
        this.service = service;
    }

    @PostMapping("/create-checkout-session/shop-order-id/{id}")
    public ResponseEntity<String>  createCheckoutSessionByShopOrderId(@PathVariable UUID id) throws StripeException {
        String checkoutUrl = this.service.createCheckoutSessionByShopOrderId(id);
        return new ResponseEntity<>(checkoutUrl, HttpStatus.OK);
    }

}
