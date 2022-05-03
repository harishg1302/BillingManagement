package com.hnm.billing.controller;

import com.hnm.billing.model.Role;
import com.hnm.billing.model.User;
import com.hnm.billing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String welcome() {

        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user") User user, Model model) {
        user.setPassword(Base64.getEncoder().encodeToString(user.getPassword().getBytes()));
        user.setRole(Role.CUSTOMER);
        User savedUser = userService.saveUser(user);
        model.addAttribute("savedUser", savedUser);
        return "login";
    }

    @GetMapping("/getByEmailId/{emailId}")
    @ResponseBody
    public User getByEmailId(@PathVariable("emailId") String emailId){
        return userService.getByEmailId(emailId);
    }

    @GetMapping("/allUsers")
    public String getAllUsers(Model model){
        List<User> users = userService.getAllUsers();
        model.addAttribute("allUsers", users);
        return "allUsers";
    }
}
