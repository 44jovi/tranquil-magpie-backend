package com.tranquilmagpie.spring.api.payment;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreatePaymentIntentResponse {

    String id;
    String clientSecret;

}
