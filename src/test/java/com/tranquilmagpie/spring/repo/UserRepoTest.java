package com.tranquilmagpie.spring.repo;

import com.tranquilmagpie.spring.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

// TODO: review converting this to a pure unit test using mocks
@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
class UserRepoTest {

    @Autowired
    private UserRepo repo;

    private final User user1 = new User();

    @BeforeEach
    void testSetup(){
        user1.setEmail("ross1@test.com");
        user1.setUsername("ross1");
        user1.setFirstName("ross");
        user1.setLastName("geller");
        user1.setDob(LocalDate.parse("1967-10-18"));
    }

    @Test
    void testFindByUuid() {
        User savedUser = repo.save(user1);
        // TODO: review usage of .isPresent()
        User foundUser = repo.findByUuid(savedUser.getUuid()).get();

        assertEquals(savedUser.getUuid(), foundUser.getUuid());
        assertEquals("ross1@test.com", foundUser.getEmail());
        assertEquals("ross1", foundUser.getUsername());
        assertEquals("ross", foundUser.getFirstName());
        assertEquals("geller", foundUser.getLastName());
        assertEquals(LocalDate.parse("1967-10-18"), foundUser.getDob());

        repo.deleteByUuid(savedUser.getUuid());
    }

    @Test
    void testDeleteByUuid() {
        User savedUser = repo.save(user1);

        Long dbRowsDeleted = repo.deleteByUuid(savedUser.getUuid());

        assertEquals(1, dbRowsDeleted);
    }

}
