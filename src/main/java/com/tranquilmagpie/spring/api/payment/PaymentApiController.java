package com.tranquilmagpie.spring.api.payment;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentApiController {

    @Value("${stripe.api.key}")
    private String apiKey;

    // TODO: return ResponseEntity
    @PostMapping("/payment-intent")
    public CreatePaymentIntentResponse createPaymentIntent(@RequestBody CreatePaymentIntentRequest req ) throws StripeException {
        Stripe.apiKey = apiKey;

        PaymentIntentCreateParams params = new PaymentIntentCreateParams.Builder()
                .setAmount(req.getAmount())
                .setCurrency(req.getCurrency())
                .setDescription(req.getDescription())
                .build();

        PaymentIntent paymentIntent = PaymentIntent.create(params);

        return new CreatePaymentIntentResponse(paymentIntent.getId(), paymentIntent.getClientSecret());
    }

}
