package com.htdp1.deptspring.home.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
	@Value("${spring.application.name}")
	public String applicationName;
	
	@GetMapping("/")
	public String index(HttpSession session) {
		return "Hello " + applicationName + " " + session.getId();
	}
}
