package com.tranquilmagpie.spring.repo;

import com.tranquilmagpie.spring.model.User;
import com.tranquilmagpie.spring.model.UserAddress;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

// TODO: review converting this to a pure unit test using mocks
@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
class UserAddressRepoSpringBootTest {

    @Autowired
    private UserAddressRepo userAddressRepo;
    @Autowired
    private UserRepo userRepo;
    private UserAddress userAddress1 = new UserAddress();
    private User user1 = new User();
    private UserAddress savedUserAddress;

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

        userRepo.save(user1);

        userAddress1 = UserAddress.builder()
                .userId(user1.getId())
                .line_1("1 ross street")
                .city("rossville")
                .postcode("ROS1S23")
                .build();

        savedUserAddress = userAddressRepo.save(userAddress1);
    }

    @AfterEach
    void tearDown(){
        userRepo.deleteById(user1.getId());
    }

    @Test
    void testFindById() {
        UserAddress foundUserAddress = userAddressRepo.findById(savedUserAddress.getId()).get();

        assertEquals(savedUserAddress.getId(), foundUserAddress.getId());
        assertEquals(user1.getId(), foundUserAddress.getUserId());
        assertEquals("1 ross street", foundUserAddress.getLine_1());
        assertEquals("rossville", foundUserAddress.getCity());
        assertEquals("ROS1S23", foundUserAddress.getPostcode());

        userAddressRepo.deleteById(savedUserAddress.getId());
    }

    @Test
    void testDeleteById() {
        userAddressRepo.deleteById(savedUserAddress.getId());
        Optional<UserAddress> result = userAddressRepo.findById(savedUserAddress.getId());

        assertEquals(Optional.empty(), result);
    }

}
