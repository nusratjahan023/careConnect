package com.careconnect.userservice.service.impl;

import com.careconnect.userservice.enums.Role;
import com.careconnect.userservice.entity.AppUser;
import com.careconnect.userservice.entity.UserDetails;
import com.careconnect.userservice.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserServiceImplTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserServiceImpl userService;

    private AppUser user;
    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        user = new AppUser();
        user.setFirstName("Test");
        user.setLastName("User");
        user.setEmail("test@example.com");
        user.setPhone("1234567890");
        user.setAddress("123 Street");
        user.setRole(Role.CLIENT);
        user.setPassword("secret");
        user.setConfirmPassword("secret");

        UserDetails details = new UserDetails();
        details.setBio("Experienced caregiver");
        user.setUserDetails(details);

        userRepository.save(user);
    }

    @AfterEach
    void tearDown() throws Exception {
        user = null;
        userRepository.deleteAll();
        closeable.close();
    }

    @Test
    void testGetAllUsers() {
        List<AppUser> users = userService.getAllUsers();
        assertThat(users).isNotEmpty();
    }

    @Test
    void testGetUserById() {
        AppUser found = userService.getUserById(user.getId());
        assertThat(found).isNotNull();
        assertThat(found.getEmail()).isEqualTo("test@example.com");
    }

    @Test
    void testGetUsersByRole() {
        List<AppUser> clients = userService.getUsersByRole("client");
        assertThat(clients).hasSize(1);
    }

    @Test
    void testAddUser() {
        AppUser newUser = new AppUser();
        newUser.setFirstName("Alice");
        newUser.setLastName("Smith");
        newUser.setEmail("alice@example.com");
        newUser.setPhone("9999999999");
        newUser.setRole(Role.CAREGIVER);
        newUser.setPassword("pwd");
        newUser.setConfirmPassword("pwd");
        newUser.setAddress("xyz st");
        newUser.setUserDetails(new UserDetails());

        AppUser saved = userService.addUser(newUser);
        assertThat(saved.getId()).isNotNull();
        assertThat(userRepository.findByEmail("alice@example.com")).isNotNull();
    }

    @Test
    void testUpdateUser() {
        AppUser updated = new AppUser();
        updated.setFirstName("Updated");
        updated.setLastName("Name");
        updated.setEmail("updated@example.com");

        AppUser result = userService.updateUser(user.getId(), updated);

        assertThat(result).isNotNull();
        assertThat(result.getEmail()).isEqualTo("updated@example.com");
    }

    @Test
    void testDeleteUser() {
        boolean deleted = userService.deleteUser(user.getId());
        assertThat(deleted).isTrue();
        assertThat(userRepository.findById(user.getId())).isEmpty();
    }

    @Test
    void testAuthenticate_Success() {
        AppUser found = userService.authenticate("test@example.com", "secret");
        assertThat(found).isNotNull();
        assertThat(found.getEmail()).isEqualTo("test@example.com");
    }

    @Test
    void testAuthenticate_Failure() {
        AppUser found = userService.authenticate("test@example.com", "wrong");
        assertThat(found).isNull();
    }
}
