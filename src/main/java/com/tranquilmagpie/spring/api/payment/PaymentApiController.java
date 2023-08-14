package com.tranquilmagpie.spring.api.payment;

import com.stripe.exception.StripeException;
import com.tranquilmagpie.spring.model.shoporder.ShopOrder;
import com.tranquilmagpie.spring.service.payment.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<CheckoutSessionResponse> createCheckoutSessionByShopOrderId(@PathVariable UUID id) throws StripeException {
        CheckoutSessionResponse checkoutSessionResponse = this.service.createCheckoutSessionByShopOrderId(id);
        return new ResponseEntity<>(checkoutSessionResponse, HttpStatus.CREATED);
    }

    @PatchMapping("/update-status/shop-order-id/{id}")
    public ResponseEntity<ShopOrder> updatePaymentStatus(@PathVariable UUID id) throws StripeException {
        ShopOrder shopOrder = this.service.updatePaymentStatus(id);
        return new ResponseEntity<>(shopOrder, HttpStatus.OK);
    }

}
