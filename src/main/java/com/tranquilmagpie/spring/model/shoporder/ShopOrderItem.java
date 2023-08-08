package com.tranquilmagpie.spring.model.shoporder;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

// TODO: review/add constraints on generated constructors and setters
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "shop_order_item")
@Schema(name = "shop_order_item", description = "item (product) in user's shop order")
public class ShopOrderItem {

    @EmbeddedId
    private ShopOrderItemId id;
    private String productName;
    private BigDecimal productPrice;
    private int qty;
    private BigDecimal priceTotal;

}
