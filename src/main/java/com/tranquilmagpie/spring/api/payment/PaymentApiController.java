package com.tranquilmagpie.spring.api.payment;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;

import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/payment")
public class PaymentApiController {

    @Value("${stripe.api.key}")
    private String apiKey;

    @PostMapping("/create-checkout-session")
    public ResponseEntity<String> createCheckoutSession() throws StripeException {
        Stripe.apiKey = apiKey;

        List<SessionCreateParams.LineItem> lineItems = new ArrayList<>();

        // TODO: loop through list of s-o-i and add them to lineItems
        lineItems.add(
                SessionCreateParams.LineItem.builder()
                        .setPriceData(
                                SessionCreateParams.LineItem.PriceData.builder()
                                        .setCurrency("gbp")
                                        .setProductData(
                                                SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                        // TODO: insert product name from s-o-i
                                                        .setName("blah")
                                                        .build()
                                        )
                                        // TODO: insert price from s-o-i
                                        .setUnitAmount(2000L)
                                        // TODO: review .setTaxBehavior
                                        .build()
                        )
                        // For reference only. Do not allow customer to amend shop order items at checkout page
                        // .setAdjustableQuantity(
                        //     SessionCreateParams.LineItem.AdjustableQuantity.builder()
                        //     .setEnabled(true)
                        //     .setMinimum(1L)
                        //     .setMaximum(10L)
                        // .build()
                        //                      )
                        // TODO: insert qty from s-o-i
                        .setQuantity(3L)
                        .build()
        );

        SessionCreateParams params = SessionCreateParams.builder()
                // previously .setLineItems()
                .addAllLineItem(lineItems)
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl("https://example.com/success")
                .setCancelUrl("https://example.com/cancel")
                .build();

        Session session = Session.create(params);
        System.out.println(session.getUrl());
        String checkoutUrl = session.getUrl();
        return new ResponseEntity<>(checkoutUrl, HttpStatus.OK);
    }

}
