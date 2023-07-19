package com.tranquilmagpie.spring.model.shoporder;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "shop_order")
@Schema(name = "shop_order", description = "user's shop order")
public class ShopOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private UUID userId;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Instant orderDateTime;

    private BigDecimal orderTotal;

    @Enumerated(EnumType.STRING)
    private ShopOrderStatus orderStatus;

    // TODO: change to enum when payment methods decided
    private String paymentMethod;

    private String shippingAddress;

}
