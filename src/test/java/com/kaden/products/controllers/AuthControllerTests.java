package com.kaden.products.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import com.kaden.products.data.UserEntity;
import com.kaden.products.data.UsersRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void registration_PersistsUser() throws Exception {
        mockMvc.perform(post("/register")
                .param("username", "reguser")
                .param("password", "password123")
                .with(csrf()))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/login?registered"));

        UserEntity user = usersRepository.findByUsername("reguser").orElse(null);
        assertNotNull(user);
        assertEquals("reguser", user.getUsername());
    }

    @Test
    public void registration_EncodesPassword() throws Exception {
        mockMvc.perform(post("/register")
                .param("username", "encuser")
                .param("password", "mypassword")
                .with(csrf()))
            .andExpect(status().is3xxRedirection());

        UserEntity user = usersRepository.findByUsername("encuser").orElse(null);
        assertNotNull(user);
        assertNotEquals("mypassword", user.getPassword());
        assertTrue(passwordEncoder.matches("mypassword", user.getPassword()));
    }
}
