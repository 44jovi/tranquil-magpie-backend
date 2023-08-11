package com.tranquilmagpie.spring.service.impl.payment;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import com.tranquilmagpie.spring.model.shoporder.ShopOrderItem;
import com.tranquilmagpie.spring.repo.shoporder.ShopOrderItemRepo;
import com.tranquilmagpie.spring.service.payment.PaymentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Primary
@Service
public class PaymentServiceImpl implements PaymentService {

    @Value("${stripe.api.key}")
    private String apiKey;

    private final ShopOrderItemRepo shopOrderItemRepo;

    public PaymentServiceImpl(ShopOrderItemRepo shopOrderItemRepo) {
        super();
        this.shopOrderItemRepo = shopOrderItemRepo;
    }

    @Override
    public String createCheckoutSessionByShopOrderId(UUID shopOrderID) throws StripeException{
        Stripe.apiKey = apiKey;

        List<ShopOrderItem> shopOrderItems = this.shopOrderItemRepo.findByIdShopOrderId(shopOrderID);

        List<SessionCreateParams.LineItem> lineItems = new ArrayList<>();

        for (ShopOrderItem item : shopOrderItems) {

        lineItems.add(
                SessionCreateParams.LineItem.builder()
                        .setPriceData(
                                SessionCreateParams.LineItem.PriceData.builder()
                                        .setCurrency("gbp")
                                        .setProductData(
                                                SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                        .setName(item.getProductName())
                                                        .build()
                                        )
                                        .setUnitAmount(item.getProductPrice()
                                                .multiply(BigDecimal.valueOf(100))
                                                .longValue())
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
                        .setQuantity((long) item.getQty())
                        .build()
        );
        }

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
        // TODO: return more useful object for frontend to consume
        return session.getUrl();
    }
}
