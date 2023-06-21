package com.tranquilmagpie.spring.repo;

import com.tranquilmagpie.spring.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
public class UserRepoTest {

    @Autowired
    private UserRepo userRepo;

    @Test
    public void testFindByUuid() {

        // TODO: review alternative as this is dependent on an entity with this UUID in the database
        UUID uuid = UUID.fromString("dfb68f04-b0ac-42a9-b9a3-97f843791f4c");

        // TODO: review usage of .isPresent()
        User user = userRepo.findByUuid(uuid).get();

        assertEquals(user.getUuid(), uuid);
        assertEquals("joe1@test.com", user.getEmail());
        assertEquals("joe1", user.getUsername());
        assertEquals("joe", user.getFirstName());
        assertEquals("joeman", user.getLastName());
        assertEquals(LocalDate.parse("1900-12-31"), user.getDob());
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
