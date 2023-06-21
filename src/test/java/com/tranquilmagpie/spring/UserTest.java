package com.tranquilmagpie.spring;

import com.tranquilmagpie.spring.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    User user1 = new User();
    User user2 = new User();

    @BeforeEach
    void setup() {
        // Alternative method: UUID.randomUUID()
        user1.setUuid(UUID.fromString("446d5db4-70dc-433e-a4f8-c2bbec0d25ab"));
        user1.setEmail("joey@test.com");
        user1.setFirstName("joey");
        user1.setLastName("tribbiani");
        user1.setDob(LocalDate.parse("1968-01-09"));

        user2.setUuid(UUID.fromString("9fd17a2f-8bb2-4289-906a-4b7ae16c080e"));
        user2.setEmail("chandler@test.com");
        user2.setFirstName("chandler");
        user2.setLastName("bing");
        user2.setDob(LocalDate.parse("1968-04-08"));
    }

    @Test
    public void testGetters() {
        assertEquals(UUID.fromString("446d5db4-70dc-433e-a4f8-c2bbec0d25ab"), user1.getId());
        assertEquals("joey@test.com", user1.getEmail());
        assertEquals("joey", user1.getFirstName());
        assertEquals("tribbiani", user1.getLastName());
        assertEquals(LocalDate.parse("1968-01-09"), user1.getDob());
    }

    @Test
    public void testToString() {
        assertEquals(
                "class User {\n" +
                        "    id: 446d5db4-70dc-433e-a4f8-c2bbec0d25ab\n" +
                        "    email: joey@test.com\n" +
                        "    username: null\n" +
                        "    firstName: joey\n" +
                        "    lastName: tribbiani\n" +
                        "    dob: 1968-01-09\n" +
                        "}"
                , user1.toString());
    }

    @Test
    public void testEquals() {
        assertTrue(user1.equals(user1));
        assertFalse(user1.equals(null));
        assertFalse(user1.equals(user2));
    }

    @Test
    public void testHashCode() {
        assertEquals(-1059597897, user1.hashCode());
        assertEquals(-287440644, user2.hashCode());
    }

}
