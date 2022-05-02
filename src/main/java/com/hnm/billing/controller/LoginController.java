package com.hnm.billing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

	@GetMapping("/")
	public String welcome() {

		return "login";
	}

	@PostMapping("/login")
	public String login() {

		return "login";
	}
}
