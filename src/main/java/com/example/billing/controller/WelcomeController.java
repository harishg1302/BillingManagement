package com.example.billing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class WelcomeController {

	@GetMapping("/")
	public String welcome(Map<String, Object> model) {

		model.put("message", "Welcome to boot");

		return "hello";
	}
}
