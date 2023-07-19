package com.tranquilmagpie.spring.model.shoporder;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

// This class represents the composite key for a shop order item
// TODO: check which constructors may be required
@Data
@Embeddable
public class ShopOrderItemId implements Serializable {

    @Column(name = "shop_order_id")
    private UUID shopOrderId;

    @Column(name = "product_id")
    private UUID productId;

}
