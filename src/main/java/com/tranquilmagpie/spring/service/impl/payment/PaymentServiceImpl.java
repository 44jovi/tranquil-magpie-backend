package com.tranquilmagpie.spring.service.impl.payment;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import com.tranquilmagpie.spring.api.payment.CheckoutSessionResponse;
import com.tranquilmagpie.spring.model.shoporder.ShopOrder;
import com.tranquilmagpie.spring.model.shoporder.ShopOrderItem;
import com.tranquilmagpie.spring.model.shoporder.ShopOrderStatus;
import com.tranquilmagpie.spring.repo.shoporder.ShopOrderItemRepo;
import com.tranquilmagpie.spring.service.payment.PaymentService;
import com.tranquilmagpie.spring.service.shoporder.ShopOrderService;
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

    private final ShopOrderService shopOrderService;
    private final ShopOrderItemRepo shopOrderItemRepo;

    public PaymentServiceImpl(ShopOrderService shopOrderService, ShopOrderItemRepo shopOrderItemRepo) {
        super();
        this.shopOrderService = shopOrderService;
        // TODO: use service instead
        this.shopOrderItemRepo = shopOrderItemRepo;
    }

    @Override
    public CheckoutSessionResponse createCheckoutSessionByShopOrderId(UUID shopOrderID) throws StripeException {

        try {
            Stripe.apiKey = apiKey;

            ShopOrder shopOrder = this.shopOrderService.getById(shopOrderID);

            if (shopOrder.getOrderStatus() == ShopOrderStatus.CONFIRMED_AWAITING_PAYMENT) {
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
                        .setSuccessUrl("https://example.com/success" + "?shopOrderId=" + shopOrderID)
                        .setCancelUrl("https://example.com/cancel")
                        .setPaymentIntentData(new SessionCreateParams.PaymentIntentData.Builder()
                                .setDescription("Shop Order ID: " + shopOrderID) // Set the order ID here
                                .build())
                        .build();

                Session session = Session.create(params);
                shopOrderService.updateStripeCheckoutSessionId(shopOrderID, session.getId());
                System.out.println(session.getUrl());

                return CheckoutSessionResponse.builder()
                        .shopOrderId(shopOrderID)
                        .checkoutSessionId(session.getId())
                        .checkoutUrl(session.getUrl())
                        .build();
            } else {
                throw new Exception("Shop order status is not 'CONFIRMED_AWAITING_PAYMENT'");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        // TODO: review
        return null;
    }

    public ShopOrder updatePaymentStatus(UUID shopOrderId) throws StripeException {
        Stripe.apiKey = apiKey;

        String checkoutSessionId = shopOrderService.getById(shopOrderId).getStripeCheckoutSessionId();

        Session session = Session.retrieve(checkoutSessionId);

        String paymentIntentId;
        if (session.getPaymentIntent() == null) {
            return this.shopOrderService.getById(shopOrderId);
        } else {
            paymentIntentId = session.getPaymentIntent();
            shopOrderService.updateStripePaymentIntentId(shopOrderId, paymentIntentId);
        }

        PaymentIntent intent = PaymentIntent.retrieve(paymentIntentId);

        if (intent.getStatus().equals("succeeded")) {
            // TODO: review usage of Stripe's .getPaymentMethodTypes()
            //   then decide if payment method in shop order should be a string or list of strings
            this.shopOrderService.confirmPayment(shopOrderId, intent.getPaymentMethodTypes().toString());
        }

        return this.shopOrderService.getById(shopOrderId);
    }

}
