package com.htdp1.deptspring.dept.controller;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.htdp1.deptspring.dept.model.Department;
import com.htdp1.deptspring.dept.service.DepartmentService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/v1")
@AllArgsConstructor
@Slf4j
public class DepartmentController {

	private DepartmentService departmentService;

	@GetMapping(value = "/departmentsNoCache")
	public List<Department> getDepartmentsNoCache() {
		log.debug("getDepartments");

		return departmentService.getDepartments();
	}

	@GetMapping(value = "/departments")
	@Cacheable(cacheNames = "departments", key = "'findAll'", cacheManager = "cacheManager")
	public List<Department> getDepartments() {
		log.debug("getDepartments");

		return departmentService.getDepartments();
	}

	@GetMapping(value = "/department/{deptNo}")
	@Cacheable(cacheNames = "departments", key = "#deptNo", cacheManager = "cacheManager")
	public Department getDepartment(@PathVariable String deptNo) {
		log.debug("getDepartment");

		return departmentService.getDepartment(deptNo);
	}

	@PutMapping(value = "/department")
	@CacheEvict(cacheNames = "departments", key = "'findAll'", cacheManager = "cacheManager")
	@CachePut(cacheNames = "departments", key = "#departmentTable.deptNo", cacheManager = "cacheManager")
	public int setDepartments(@RequestBody Department departmentTable) {
		log.debug("setDepartments");
		
		return departmentService.setDepartment(departmentTable);
	}

	@DeleteMapping(value = "/department/{deptNo}")
	@Caching(evict = { 
			@CacheEvict(cacheNames = "departments", key = "'findAll'", cacheManager = "cacheManager"),
			@CacheEvict(cacheNames = "departments", key = "#deptNo", cacheManager = "cacheManager") 
			}
	)
	public int deleteDepartment(@PathVariable String deptNo) {
		log.debug("deleteDepartment");

		return departmentService.deleteDepartment(deptNo);
	}

}
