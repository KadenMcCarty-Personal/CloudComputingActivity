package com.kaden.products.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kaden.products.data.UserEntity;
import com.kaden.products.data.UsersRepository;

@Controller
@RequestMapping("/admin/users")
public class UserAdminController {

    private final UsersRepository usersRepository;

    public UserAdminController(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @GetMapping("")
    public String showAllUsers(Model model) {
        model.addAttribute("users", usersRepository.findAll());
        model.addAttribute("title", "User Admin");
        return "userAdmin";
    }

    @GetMapping("/edit/{id}")
    public String showEditUser(@PathVariable("id") Long id, Model model) {
        UserEntity user = usersRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid user ID: " + id));
        model.addAttribute("user", user);
        model.addAttribute("title", "Edit User");
        return "editUser";
    }

    @PostMapping("/edit")
    public String processEditUser(@RequestParam Long id, @RequestParam String role, @RequestParam boolean enabled) {
        UserEntity user = usersRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid user ID: " + id));
        user.setRole(role);
        user.setEnabled(enabled);
        usersRepository.save(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/delete/{id}")
    public String showDeleteUser(@PathVariable("id") Long id, Model model) {
        UserEntity user = usersRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid user ID: " + id));
        model.addAttribute("user", user);
        model.addAttribute("title", "Delete User");
        return "confirmDeleteUser";
    }

    @PostMapping("/delete")
    public String processDeleteUser(@RequestParam Long id) {
        usersRepository.deleteById(id);
        return "redirect:/admin/users";
    }
}
