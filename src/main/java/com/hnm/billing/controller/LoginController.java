package com.hnm.billing.controller;

import com.hnm.billing.dto.LoginDTO;
import com.hnm.billing.model.Role;
import com.hnm.billing.model.User;
import com.hnm.billing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.Base64;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;

	@GetMapping({ "/", "/login", "/user/login" })
	public String welcome() {

		return "login";
	}

	@PostMapping({ "/login", "/user/login" })
	public String login(@ModelAttribute LoginDTO loginDTO, HttpSession session, Model model) {

		loginDTO.setPassword(Base64.getEncoder().encodeToString(loginDTO.getPassword().getBytes()));
		User loggedInUser = userService.loginUser(loginDTO);
		if (loggedInUser != null) {
			session.setAttribute("user", loggedInUser);
			if (loggedInUser.getRole().equals(Role.CUSTOMER)) {
				return "userHome";
			} else {
				return "adminHome";
			}
		} else {
			model.addAttribute("errorMessage", "UserId or password is incorrect");
			return "login";
		}
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {

		session.invalidate();
		return "login";
	}

	@GetMapping("/register")
	public String register() {

		return "register";
	}
}
