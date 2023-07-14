package com.tranquilmagpie.spring.model;

public enum ShopOrderStatus {
    PENDING,
    CONFIRMED_AWAITING_PAYMENT,
    PAID_AWAITING_SHIPMENT,
    SHIPPED,
    CANCELLED
}
