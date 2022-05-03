package com.hnm.billing.controller;

import com.hnm.billing.dto.LoginDTO;
import com.hnm.billing.model.User;
import com.hnm.billing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.Base64;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;

	@GetMapping("/")
	public String welcome() {

		return "login";
	}

	@PostMapping("/login")
	public String login(@ModelAttribute LoginDTO loginDTO, HttpSession session) {
		loginDTO.setPassword(Base64.getEncoder().encodeToString(loginDTO.getPassword().getBytes()));
		User loggedInUser = userService.loginUser(loginDTO);
		if(loggedInUser != null){
			session.setAttribute("user", loggedInUser);
			return "home";
		} else {
			return "login";
		}

	}
}
