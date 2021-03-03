package com.htdp1.deptspring.dept.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.htdp1.deptspring.dept.model.Department;
import com.htdp1.deptspring.dept.service.DepartmentService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/v1")
@Slf4j
public class DepartmentController {
	@Autowired
	DepartmentService departmentService;

	@GetMapping(value = "/departments")
	@ResponseBody
	@Cacheable(cacheNames = "departments", cacheManager = "cacheManager")
	public List<Department> getDepartments() {
		log.debug("getDepartments");

		return departmentService.getDepartments();
	}

	@GetMapping(value = "/departments/{deptNo}")
	@ResponseBody
	@Cacheable(cacheNames = "departments", key = "#deptNo", cacheManager = "cacheManager")
	public Department getDepartment(@PathVariable String deptNo) {
		log.debug("getDepartment");

		return departmentService.getDepartment(deptNo);
	}

	@DeleteMapping(value = "/departments/{deptNo}")
	@ResponseBody
	@CacheEvict(cacheNames = "departments", key = "#deptNo", cacheManager = "cacheManager")
	public void deleteDepartment(@PathVariable String deptNo) {
		log.debug("deleteDepartment");

		departmentService.deleteDepartment(deptNo);
	}
}
