package com.tranquilmagpie.spring;

import com.tranquilmagpie.spring.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


import java.time.LocalDate;
import java.util.UUID;

public class UserTest {
    @Test
    public void testUser() {

        User user = new User();
        // Alternative method: UUID.randomUUID()
        user.setUuid(UUID.fromString("446d5db4-70dc-433e-a4f8-c2bbec0d25ab"));
        user.setEmail("joey@test.com");
        user.setFirstName("joey");
        user.setLastName("tribbiani");
        user.setDob(LocalDate.parse("1968-01-01"));

        assertEquals(UUID.fromString("446d5db4-70dc-433e-a4f8-c2bbec0d25ab"), user.getId());
        assertEquals("joey@test.com", user.getEmail());
        assertEquals("joey", user.getFirstName());
        assertEquals("tribbiani", user.getLastName());
        assertEquals(LocalDate.parse("1968-01-01"), user.getDob());

    }
}
