package com.tranquilmagpie.spring.repo;

import com.tranquilmagpie.spring.model.shoporder.ShopOrder;
import com.tranquilmagpie.spring.model.user.User;
import com.tranquilmagpie.spring.repo.shoporder.ShopOrderRepo;
import com.tranquilmagpie.spring.repo.user.UserRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Optional;

import static com.tranquilmagpie.spring.model.shoporder.ShopOrderStatus.PENDING;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
@SpringBootTest
class ShopOrderRepoSpringBootTest {

    @Autowired
    private ShopOrderRepo shopOrderRepo;
    @Autowired
    private UserRepo userRepo;
    private ShopOrder shopOrder1 = new ShopOrder();
    private User user1 = new User();
    private BigDecimal orderTotal1 = new BigDecimal("12.34");
    private ShopOrder savedShopOrder;
    private User savedUser1;

    @BeforeEach
    void setUp() {
        user1 = User.builder()
                .email("ross1@test.com")
                .username("ross1")
                .givenName("ross")
                .familyName("geller")
                .dob(LocalDate.parse("1967-10-18"))
                .password("$2a$10$2malfc6GDzJiotrLd9QI5ea5oyulYbKaAie.Tg8fAgwmlVN4Rvoy6")
                .build();

        savedUser1 = userRepo.save(user1);

        shopOrder1 = ShopOrder.builder()
                .userId(savedUser1.getId())
                .orderDateTime(Instant.now())
                .orderTotal(orderTotal1)
                .orderStatus(PENDING)
                .paymentMethod("credit card")
                .shippingAddress("example address 1")
                .build();

        savedShopOrder = shopOrderRepo.save(shopOrder1);
    }

    @AfterEach
    void tearDown() {
        userRepo.deleteById(savedUser1.getId());
    }

    @Test
    void testFindById() {
        ShopOrder foundShopOrder = shopOrderRepo.findById(savedShopOrder.getId()).get();

        assertEquals(savedShopOrder.getId(), foundShopOrder.getId());
        assertEquals(user1.getId(), foundShopOrder.getUserId());
        assertEquals(Instant.class, foundShopOrder.getOrderDateTime().getClass());
        assertEquals(orderTotal1, foundShopOrder.getOrderTotal());
        assertEquals(PENDING, foundShopOrder.getOrderStatus());
        assertEquals("credit card", foundShopOrder.getPaymentMethod());
        assertEquals("example address 1", foundShopOrder.getShippingAddress());

        shopOrderRepo.deleteById(savedShopOrder.getId());
    }

    @Test
    void testDeleteById() {
        shopOrderRepo.deleteById(savedShopOrder.getId());
        Optional<ShopOrder> result = shopOrderRepo.findById(savedShopOrder.getId());

        assertEquals(Optional.empty(), result);
    }

}
