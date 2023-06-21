package com.tranquilmagpie.spring.repo;

import com.tranquilmagpie.spring.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
public class UserRepoTest {

    @Autowired
    private UserRepo userRepo;

    private User user1 = new User();

    @BeforeEach
    public void testSetup(){
        user1.setEmail("ross1@test.com");
        user1.setUsername("ross1");
        user1.setFirstName("ross");
        user1.setLastName("geller");
        user1.setDob(LocalDate.parse("1967-10-18"));
    }

    @Test
    public void testFindByUuid() {
        User savedUser = userRepo.save(user1);
        // TODO: review usage of .isPresent()
        User foundUser = userRepo.findByUuid(savedUser.getUuid()).get();

        assertEquals(savedUser.getUuid(), foundUser.getUuid());
        assertEquals("ross1@test.com", foundUser.getEmail());
        assertEquals("ross1", foundUser.getUsername());
        assertEquals("ross", foundUser.getFirstName());
        assertEquals("geller", foundUser.getLastName());
        assertEquals(LocalDate.parse("1967-10-18"), foundUser.getDob());

        userRepo.deleteByUuid(savedUser.getUuid());
    }

    @Test
    public void testDeleteByUuid() {
        User savedUser = userRepo.save(user1);

        Long dbRowsDeleted = userRepo.deleteByUuid(savedUser.getUuid());

        assertEquals(1, dbRowsDeleted);
    }

}
