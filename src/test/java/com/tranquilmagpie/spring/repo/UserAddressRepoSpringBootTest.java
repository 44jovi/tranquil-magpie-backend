package com.tranquilmagpie.spring.repo;

import com.tranquilmagpie.spring.model.UserAddress;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

// TODO: review converting this to a pure unit test using mocks
@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
class UserAddressRepoSpringBootTest {

    @Autowired
    private UserAddressRepo repo;

    private UserAddress userAddress1 = new UserAddress();
    private final UUID uuid1 = UUID.fromString("812de802-037f-4230-b2e3-f6b45a4c6882");

    @BeforeEach
    void setUp() {

        userAddress1 = UserAddress.builder()
                .userId(uuid1)
                .line_1("1 ross street")
                .city("rossville")
                .postcode("ROS1S23")
                .build();
    }

    @Test
    void testFindById() {
        UserAddress savedUserAddress = repo.save(userAddress1);
        // TODO: review usage of .isPresent()
        UserAddress foundUserAddress = repo.findById(savedUserAddress.getId()).get();

        assertEquals(savedUserAddress.getId(), foundUserAddress.getId());
        assertEquals(uuid1, foundUserAddress.getUserId());
        assertEquals("1 ross street", foundUserAddress.getLine_1());
        assertEquals("rossville", foundUserAddress.getCity());
        assertEquals("ROS1S23", foundUserAddress.getPostcode());

        repo.deleteById(savedUserAddress.getId());
    }

    @Test
    void testDeleteById() {
        UserAddress savedUserAddress = repo.save(userAddress1);
        repo.deleteById(savedUserAddress.getId());
        Optional<UserAddress> result = repo.findById(savedUserAddress.getId());

        assertEquals(Optional.empty(), result);
    }

}
