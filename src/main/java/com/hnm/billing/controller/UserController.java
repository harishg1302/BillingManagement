package com.hnm.billing.controller;

import com.hnm.billing.model.User;
import com.hnm.billing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Base64;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/")
	public String welcome(Map<String, Object> model) {

		model.put("message", "Welcome to boot");

		return "hello";
	}

	@PostMapping("/register")
	public RedirectView register(@ModelAttribute("user") User user, RedirectAttributes redirectAttributes) {
		final RedirectView redirectView = new RedirectView("/book/addBook", true);
		user.setPassword(Base64.getEncoder().encodeToString(user.getPassword().getBytes()));
		User savedUser = userService.saveUser(user);
		redirectAttributes.addFlashAttribute("savedUser", savedUser);
		redirectAttributes.addFlashAttribute("saveUserSuccess", true);
		return redirectView;
	}
}
