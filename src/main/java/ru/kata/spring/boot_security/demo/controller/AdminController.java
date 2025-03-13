package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;
@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        //model.addAttribute("allRoles", roleService.getAllRoles());
        return "users";
    }

    @GetMapping("/users/add")
    public String addUserForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("allRoles", roleService.getAllRoles());
        return "add-user";
    }

    @PostMapping("/users/add")
    public String addUser(@ModelAttribute User user) {
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/users/edit")
    public String editUserForm(@RequestParam Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("allRoles", roleService.getAllRoles());
        return "edit-user";
    }

    @PostMapping("/users/edit")
    public String editUser(@ModelAttribute User user) {
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/users/delete")
    public String deleteUser(@RequestParam Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}