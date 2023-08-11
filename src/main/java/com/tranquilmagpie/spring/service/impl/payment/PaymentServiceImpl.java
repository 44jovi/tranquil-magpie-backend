package com.tranquilmagpie.spring.service.impl.payment;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import com.tranquilmagpie.spring.repo.shoporder.ShopOrderItemRepo;
import com.tranquilmagpie.spring.service.payment.PaymentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Primary
@Service
public class PaymentServiceImpl implements PaymentService {

    @Value("${stripe.api.key}")
    private String apiKey;

    private final ShopOrderItemRepo repo;

    public PaymentServiceImpl(ShopOrderItemRepo repo) {
        super();
        this.repo = repo;
    }

    @Override
    public String createCheckoutSession(UUID shopOrderID) throws StripeException{
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
                // TODO: frontend - create success/cancellation pages
                .setSuccessUrl("https://example.com/success")
                .setCancelUrl("https://example.com/cancel")
                .build();

        Session session = Session.create(params);
        System.out.println(session.getUrl());
        return session.getUrl();
    }
}
