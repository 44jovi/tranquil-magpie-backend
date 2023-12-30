package com.tranquilmagpie.spring.model;

import com.tranquilmagpie.spring.model.shoporder.ShopOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import static com.tranquilmagpie.spring.model.shoporder.ShopOrderStatus.PENDING;
import static com.tranquilmagpie.spring.model.shoporder.ShopOrderStatus.SHIPPED;
import static org.junit.jupiter.api.Assertions.*;

class ShopOrderTest {

    ShopOrder shopOrder1 = new ShopOrder();
    ShopOrder shopOrder2 = new ShopOrder();
    UUID uuid1 = UUID.fromString("0c7ebd59-dc67-49c8-a4ed-80810e0be975");
    UUID uuid2 = UUID.fromString("9db8d917-9bae-4eec-840f-0728611818d9");
    UUID uuid3 = UUID.fromString("69c5f7bf-2d5a-4ea0-b67a-2f27a992933e");
    UUID uuid4 = UUID.fromString("9dbbc414-b1c5-4df2-8014-1e90489f0bf7");
    Instant dateTime1 = Instant.parse("1901-01-01T01:01:01.000001Z");
    Instant dateTime2 = Instant.parse("1902-02-02T02:02:02.000002Z");
    BigDecimal orderTotal1 = new BigDecimal("12.34");
    BigDecimal orderTotal2 = new BigDecimal("56.78");


    @BeforeEach
    void setUp() {
        shopOrder1 = ShopOrder.builder()
                .id(uuid1)
                .userId(uuid2)
                .orderDateTime(dateTime1)
                .orderTotal(orderTotal1)
                .orderStatus(PENDING)
                .paymentMethod("credit card")
                .shippingAddress("example address 1")
                .build();

        shopOrder2 = ShopOrder.builder()
                .id(uuid3)
                .userId(uuid4)
                .orderDateTime(dateTime2)
                .orderTotal(orderTotal2)
                .orderStatus(SHIPPED)
                .paymentMethod("credit card")
                .shippingAddress("example address 2")
                .build();
    }

    @Test
    void testGetters() {
        assertEquals(uuid1, shopOrder1.getId());
        assertEquals(uuid2, shopOrder1.getUserId());
        assertEquals(dateTime1, shopOrder1.getOrderDateTime());
        assertEquals(orderTotal1, shopOrder1.getOrderTotal());
        assertEquals(PENDING, shopOrder1.getOrderStatus());
        assertEquals("credit card", shopOrder1.getPaymentMethod());
        assertEquals("example address 1", shopOrder1.getShippingAddress());
    }

    @Test
    void testEquals() {
        assertNotEquals(shopOrder1, shopOrder2);
    }

    @Test
    void testHashCode() {
        // Not testing for value to be the same each time
        // due to it changing each test suite run (due to Big Decimal?)
        assertNotEquals(shopOrder1.hashCode(), shopOrder2.hashCode());
    }

}
