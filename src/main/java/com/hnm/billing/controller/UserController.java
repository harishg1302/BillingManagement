package com.hnm.billing.controller;

import com.hnm.billing.model.Role;
import com.hnm.billing.model.User;
import com.hnm.billing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Base64;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/register", produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String register(@RequestBody User user) {

        user.setPassword(Base64.getEncoder().encodeToString(user.getPassword().getBytes()));
        user.setRole(Role.CUSTOMER);
        user.setStatus(true);
        User savedUser = userService.saveUser(user);
        return "login";
    }

    @GetMapping("/getByEmailId/{emailId}")
    @ResponseBody
    public User getByEmailId(@PathVariable("emailId") String emailId) {
        return userService.getByEmailId(emailId);
    }

    @GetMapping("/allUsers")
    @ResponseBody
    public List<User> getAllUsers() {

        return userService.getAllUsers();
    }
}
