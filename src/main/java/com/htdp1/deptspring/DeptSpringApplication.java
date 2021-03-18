package com.htdp1.deptspring;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.htdp1.deptspring.dept.model.DepartmentRedis;
import com.htdp1.deptspring.dept.repository.DepartmentRepositoryRedis;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class DeptSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeptSpringApplication.class, args);
	}

	@Autowired
	private DepartmentRepositoryRedis departmentRepository;

	@PostConstruct
	public void init() {
		
		// redis repository delete all
		departmentRepository.deleteAll();
		
		// redis repository add data
		for (int i = 0; i < 3; i++) {
			departmentRepository.save(new DepartmentRedis("t" + i, "test" + i));
		}

		log.debug("count => " + departmentRepository.count());
	}
}
