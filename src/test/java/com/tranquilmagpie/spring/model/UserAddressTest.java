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

    @BeforeEach
    void setUp() {

        userAddress1 = UserAddress.builder()
                .id(1)
                .userId(uuid1)
                .line_1("1 joey street")
                .city("joeyville")
                .postcode("JOE1Y23")
                .build();

        userAddress2 = UserAddress.builder()
                .id(2)
                .userId(uuid2)
                .line_1("1 chandler street")
                .city("chandlerville")
                .postcode("CHA1N23")
                .build();
    }

    @Test
    void testGetters() {
        assertEquals(1, userAddress1.getId());
        assertEquals(uuid1, userAddress1.getUserId());
        assertEquals("1 joey street", userAddress1.getLine_1());
        assertEquals("joeyville", userAddress1.getCity());
        assertEquals("JOE1Y23", userAddress1.getPostcode());
    }

    @Test
    void testToString() {
        assertEquals(
                "UserAddress(id=1, userId=0a7f3adb-0fd4-4461-afec-1146b117e5c7, line_1=1 joey street, city=joeyville, postcode=JOE1Y23)"
                , userAddress1.toString());
    }

    @Test
    void testEquals() {
        assertTrue(userAddress1.equals(userAddress1));
        assertFalse(userAddress1.equals(null));
        assertFalse(userAddress1.equals(userAddress2));
    }

    @Test
    void testHashCode() {
        assertEquals(1687680028, userAddress1.hashCode());
        assertEquals(642652581, userAddress2.hashCode());
    }

}
