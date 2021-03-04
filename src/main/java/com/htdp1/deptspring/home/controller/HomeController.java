package com.htdp1.deptspring.home.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class HomeController {

	@Value("${spring.application.name}")
	public String applicationName;

	@GetMapping("/")
	public String index() {
		log.debug("index");

		return "Hello " + applicationName;
	}

	@GetMapping("/session")
	public String index(HttpSession session) {
		log.debug("index");

		return "Hello " + applicationName + " " + session.getId();
	}
}
