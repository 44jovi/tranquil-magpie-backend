package com.tranquilmagpie.spring.api.payment;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class CheckoutSessionResponse {

    private UUID shopOrderId;
    private String checkoutSessionId;
    private String checkoutUrl;

}
