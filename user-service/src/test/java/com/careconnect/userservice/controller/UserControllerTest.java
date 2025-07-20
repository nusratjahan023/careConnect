package com.careconnect.userservice.controller;

import com.careconnect.userservice.entity.AppUser;
import com.careconnect.userservice.enums.Role;
import com.careconnect.userservice.service.impl.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserServiceImpl userService;

    @MockitoBean
    private UserDetailsService userDetailsService;

    @MockitoBean
    private CertificationService certificationService;

    @MockitoBean
    private LanguageService languageService;

    @MockitoBean
    private EducationServiceImpl educationService;

    @MockitoBean
    private ReviewServiceImpl reviewService;

    private AppUser user1;
    private AppUser user2;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        user1 = new AppUser();
        user1.setId(1L);
        user1.setFirstName("Test");
        user1.setLastName("User");
        user1.setEmail("test@example.com");
        user1.setPhone("1234567890");
        user1.setAddress("123 Street");
        user1.setRole(Role.CLIENT);
        user1.setPassword("secret");

        user2 = new AppUser();
        user2.setId(2L);
        user2.setFirstName("Test 1");
        user2.setLastName("User");
        user2.setEmail("test1@example.com");
        user2.setPhone("1234567890");
        user2.setAddress("123 Street");
        user2.setRole(Role.CAREGIVER);
        user2.setPassword("secret");
    }

    @Test
    void getAllUsers() throws Exception {
        when(userService.getAllUsers()).thenReturn(List.of(user1, user2));

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2));
    }

    @Test
    void getUserById() throws Exception {
        when(userService.getUserById(1L)).thenReturn(user1);

        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("test@example.com"));
    }

    @Test
    void getUsersByRole() throws Exception {
        when(userService.getUsersByRole("CLIENT")).thenReturn(List.of(user1));

        mockMvc.perform(get("/users/role/CLIENT"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1));
    }

    @Test
    void getUsersByRole_2() throws Exception {
        when(userService.getUsersByRole("CAREGIVER")).thenReturn(List.of(user2));

        mockMvc.perform(get("/users/role/CAREGIVER"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1));
    }

    @Test
    void updateUser() throws Exception {
        when(userService.updateUser(eq(1L), any(AppUser.class))).thenReturn(user1);

        mockMvc.perform(put("/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("test@example.com"));
    }

    @Test
    void saveUserDetails() throws Exception {
        doNothing().when(userDetailsService).saveUserDetails(eq(1L), any());

        mockMvc.perform(post("/users/1/details")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"bio\": \"Experienced caregiver\"}"))
                .andExpect(status().isOk());
    }
}



