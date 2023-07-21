package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;
import ru.kata.spring.boot_security.demo.util.UserValidator;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@Validated
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final UserValidator userValidator;
    private final RoleService roleService;
    @Autowired
    public AdminController(UserService userService, RoleService roleService, UserValidator userValidator) {
        this.userService = userService;
        this.userValidator= userValidator;
        this.roleService = roleService;
    }
    @GetMapping
    public String mainPage(Principal principal, Model model) {
        User admin = userService.getByUsername(principal.getName());
        model.addAttribute("admin", admin);
        model.addAttribute("users", userService.getAllUser());
        model.addAttribute("userRoles", roleService.getAllRoles());
        model.addAttribute("userNew", new User());
        model.addAttribute("rolesNew", roleService.getAllRoles());
        return "admin";
    }
    @PostMapping("")
    public String addUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {

        userValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors()) {
            return "redirect:/user";
        }
        userService.addUser(user);
        return "redirect:/admin";
    }
    @PutMapping("/{id}")
    public String editUser(@ModelAttribute("user") @Valid User user) {
            userService.addUser(user);
        return "redirect:/admin";
    }

    @GetMapping("{id}/delete")
    public String deleteUser(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}
