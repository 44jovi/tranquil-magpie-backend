package com.tranquilmagpie.spring.model;

import com.tranquilmagpie.spring.model.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.tranquilmagpie.spring.model.user.Role.USER;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    User user1 = new User();
    User user2 = new User();

    @BeforeEach
    void setUp() {
        // Alternative method: UUID.randomUUID()
        user1.setId(UUID.fromString("446d5db4-70dc-433e-a4f8-c2bbec0d25ab"));
        user1.setEmail("joey1@test.com");
        user1.setUsername("joey1");
        user1.setGivenName("joey");
        user1.setFamilyName("tribbiani");
        user1.setDob(LocalDate.parse("1968-01-09"));
        user1.setPassword("pass123");
        user1.setRole(USER);

        user2.setId(UUID.fromString("9fd17a2f-8bb2-4289-906a-4b7ae16c080e"));
        user2.setEmail("chandler1@test.com");
        user2.setUsername("chandler1");
        user2.setGivenName("chandler");
        user2.setFamilyName("bing");
        user2.setDob(LocalDate.parse("1968-04-08"));
    }

    @Test
    void testGetters() {
        // TODO: Use mock(?)
        List<SimpleGrantedAuthority> authList = new ArrayList<>();
        authList.add(new SimpleGrantedAuthority(USER.name()));

        assertEquals(UUID.fromString("446d5db4-70dc-433e-a4f8-c2bbec0d25ab"), user1.getId());
        assertEquals("joey1@test.com", user1.getEmail());
        assertEquals("joey1", user1.getUsername());
        assertEquals("joey", user1.getGivenName());
        assertEquals("tribbiani", user1.getFamilyName());
        assertEquals(LocalDate.parse("1968-01-09"), user1.getDob());

        // Spring security
        assertEquals("pass123", user1.getPassword());
        assertTrue(user1.isAccountNonExpired());
        assertTrue(user1.isAccountNonLocked());
        assertTrue(user1.isCredentialsNonExpired());
        assertTrue(user1.isEnabled());
        assertEquals(authList, user1.getAuthorities());
    }

    @Test
    void testToString() {
        assertEquals(
                "User(id=446d5db4-70dc-433e-a4f8-c2bbec0d25ab, email=joey1@test.com, username=joey1, givenName=joey, familyName=tribbiani, dob=1968-01-09, password=pass123, role=USER)"
                , user1.toString());
    }

    @Test
    void testEquals() {
        assertNotEquals(user1, user2);
    }

    @Test
    void testHashCode() {
        assertNotEquals(user1.hashCode(), user2.hashCode());
    }

}
