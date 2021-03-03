package com.htdp1.deptspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class DeptSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeptSpringApplication.class, args);
	}
}
