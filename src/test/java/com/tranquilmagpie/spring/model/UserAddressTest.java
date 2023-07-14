package com.tranquilmagpie.spring.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class UserAddressTest {

    UserAddress userAddress1 = new UserAddress();
    UserAddress userAddress2 = new UserAddress();
    UUID uuid1 = UUID.fromString("0a7f3adb-0fd4-4461-afec-1146b117e5c7");
    UUID uuid2 = UUID.fromString("f70d7509-35c7-462f-a399-0c6dfe08777b");
    UUID uuid3 = UUID.fromString("096534b9-7add-47ca-9e64-2309d7d16c50");
    UUID uuid4 = UUID.fromString("0c000acf-3180-4049-b2ee-4f3500addbd6");

    @BeforeEach
    void setUp() {

        userAddress1 = UserAddress.builder()
                .id(uuid1)
                .userId(uuid2)
                .line_1("1 joey street")
                .city("joeyville")
                .postcode("JOE1Y23")
                .build();

        userAddress2 = UserAddress.builder()
                .id(uuid3)
                .userId(uuid4)
                .line_1("1 chandler street")
                .city("chandlerville")
                .postcode("CHA1N23")
                .build();
    }

    @Test
    void testGetters() {
        assertEquals(uuid1, userAddress1.getId());
        assertEquals(uuid2, userAddress1.getUserId());
        assertEquals("1 joey street", userAddress1.getLine_1());
        assertEquals("joeyville", userAddress1.getCity());
        assertEquals("JOE1Y23", userAddress1.getPostcode());
    }

    @Test
    void testToString() {
        assertEquals(
                "UserAddress(id=" + uuid1 + ", userId=" + uuid2 +", line_1=1 joey street, city=joeyville, postcode=JOE1Y23)"
                , userAddress1.toString());
    }

    @Test
    void testEquals() {
        assertNotEquals(userAddress1, userAddress2);
    }

    @Test
    void testHashCode() {
        assertEquals(241441493, userAddress1.hashCode());
        assertEquals(1806092588, userAddress2.hashCode());
    }

}
