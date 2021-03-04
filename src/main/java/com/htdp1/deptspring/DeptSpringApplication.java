package com.htdp1.deptspring;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import com.htdp1.deptspring.dept.model.Department;
import com.htdp1.deptspring.dept.repository.DepartmentRepository;

@SpringBootApplication
public class DeptSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeptSpringApplication.class, args);
	}

	@Autowired
	private DepartmentRepository departmentRepository;

	public @PostConstruct void init() {
		departmentRepository.deleteAll();
		departmentRepository.save(new Department("t0001", "test1"));
		departmentRepository.save(new Department("t0002", "test2"));
		departmentRepository.save(new Department("t0003", "test3"));
	}
}
