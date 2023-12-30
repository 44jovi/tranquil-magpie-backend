package com.tranquilmagpie.spring.repo;

import com.tranquilmagpie.spring.model.user.User;
import com.tranquilmagpie.spring.repo.user.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
class UserRepoSpringBootTest {

    @Autowired
    private UserRepo repo;

    private final User user1 = new User();

    @BeforeEach
    void setUp() {
        user1.setEmail("ross1@test.com");
        user1.setUsername("ross1");
        user1.setGivenName("ross");
        user1.setFamilyName("geller");
        user1.setDob(LocalDate.parse("1967-10-18"));
        user1.setPassword("pass123");
    }

    @Test
    void testFindById() {
        User savedUser = repo.save(user1);
        // TODO: review usage of .isPresent()
        User foundUser = repo.findById(savedUser.getId()).get();

        assertEquals(savedUser.getId(), foundUser.getId());
        assertEquals("ross1@test.com", foundUser.getEmail());
        assertEquals("ross1", foundUser.getUsername());
        assertEquals("ross", foundUser.getGivenName());
        assertEquals("geller", foundUser.getFamilyName());
        assertEquals(LocalDate.parse("1967-10-18"), foundUser.getDob());

        repo.deleteById(savedUser.getId());
    }

    @Test
    void testDeleteById() {
        User savedUser = repo.save(user1);
        repo.deleteById(savedUser.getId());
        Optional<User> result = repo.findById(savedUser.getId());

        assertEquals(Optional.empty(), result);
    }

}
