package com.kaden.products.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SecurityTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void adminRoutes_RedirectToLogin_WhenUnauthenticated() throws Exception {
        mockMvc.perform(get("/admin/users"))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrlPattern("**/login"));
    }

    @Test
    public void adminRoutes_Forbidden_WhenNotAdmin() throws Exception {
        mockMvc.perform(get("/admin/users")
                .with(user("regularuser").roles("USER")))
            .andExpect(status().isForbidden());
    }

    @Test
    public void adminRoutes_Accessible_WhenAdmin() throws Exception {
        mockMvc.perform(get("/admin/users")
                .with(user("adminuser").roles("ADMIN")))
            .andExpect(status().isOk());
    }

    @Test
    public void loginPage_Accessible_WhenUnauthenticated() throws Exception {
        mockMvc.perform(get("/login"))
            .andExpect(status().isOk());
    }

    @Test
    public void registerPage_Accessible_WhenUnauthenticated() throws Exception {
        mockMvc.perform(get("/register"))
            .andExpect(status().isOk());
    }
}
