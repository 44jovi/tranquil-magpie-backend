package com.tranquilmagpie.spring.api.payment;

import lombok.Data;

@Data
public class CreatePaymentIntentRequest {

    // TODO: convert shop order total from BigDecimal to long
    Long amount;
    String currency;
    String description;

}
