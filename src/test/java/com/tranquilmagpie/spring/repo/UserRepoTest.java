package com.tranquilmagpie.spring.repo;

import com.tranquilmagpie.spring.model.User;
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

    @Test
    public void testFindByUuid() {
        User user = new User();
        user.setEmail("ross1@test.com");
        user.setUsername("ross1");
        user.setFirstName("ross");
        user.setLastName("geller");
        user.setDob(LocalDate.parse("1967-10-18"));

        User savedUser = userRepo.save(user);
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
        User user = new User();
        user.setEmail("ross1@test.com");
        user.setUsername("ross1");
        user.setFirstName("ross");
        user.setLastName("geller");
        user.setDob(LocalDate.parse("1967-10-18"));

        User savedUser = userRepo.save(user);

        Long dbRowsDeleted = userRepo.deleteByUuid(savedUser.getUuid());

        assertEquals(1, dbRowsDeleted);
    }

}
