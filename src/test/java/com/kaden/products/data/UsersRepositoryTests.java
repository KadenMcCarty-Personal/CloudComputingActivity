package com.kaden.products.data;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class UsersRepositoryTests {

    @Autowired
    private UsersRepository usersRepository;

    @Test
    public void findByUsername_ReturnsUser_WhenUserExists() {
        UserEntity user = new UserEntity();
        user.setUsername("testuser");
        user.setPassword("encodedpassword");
        user.setRole("USER");
        user.setEnabled(true);
        usersRepository.save(user);

        Optional<UserEntity> found = usersRepository.findByUsername("testuser");

        assertTrue(found.isPresent());
        assertEquals("testuser", found.get().getUsername());
    }

    @Test
    public void findByUsername_ReturnsEmpty_WhenUserDoesNotExist() {
        Optional<UserEntity> found = usersRepository.findByUsername("nonexistent");

        assertFalse(found.isPresent());
    }

    @Test
    public void save_PersistsUser() {
        UserEntity user = new UserEntity();
        user.setUsername("newuser");
        user.setPassword("password123");
        user.setRole("USER");
        user.setEnabled(true);

        UserEntity saved = usersRepository.save(user);

        assertNotNull(saved.getId());
        assertEquals("newuser", saved.getUsername());
    }
}
