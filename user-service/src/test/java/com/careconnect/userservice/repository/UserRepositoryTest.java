package com.careconnect.userservice.repository;

import com.careconnect.userservice.enums.Role;
import com.careconnect.userservice.entity.AppUser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private AppUser user;

    @BeforeEach
    void setUp() {
        AppUser user = new AppUser();
        user.setFirstName("Test");
        user.setLastName("User");
        user.setEmail("test@example.com");
        user.setPhone("1234567890");
        user.setAddress("123 Street");
        user.setRole(Role.CLIENT);
        user.setPassword("secret");
        user.setConfirmPassword("secret");
        user.setUserDetails(null);

        userRepository.save(user);
    }

    @AfterEach
    void clearAfter() {
        user = null;
        userRepository.deleteAll();
    }

    @Test
    void testFindByRole() {
        List<AppUser> clients = userRepository.findByRole(Role.CLIENT);
        assertThat(clients).isNotEmpty();
    }

    @Test
    void testFindByEmail() {
        AppUser user1 = userRepository.findByEmail("test@example.com");
        assertThat(user1).isNotNull();
    }

    @Test
    void testFindByEmail_Failure() {
        AppUser user1 = userRepository.findByEmail("wrong@example.com");
        assertThat(user1).isNull();
    }
}
