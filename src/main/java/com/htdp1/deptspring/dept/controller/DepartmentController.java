package com.htdp1.deptspring.dept.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.htdp1.deptspring.dept.model.DepartmentTable;
import com.htdp1.deptspring.dept.service.DepartmentService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/v1")
@Slf4j
public class DepartmentController {
	@Autowired
	DepartmentService departmentService;

	@GetMapping(value = "/departmentsNoCache")
	@ResponseBody
	public List<DepartmentTable> getDepartmentsNoCache() {
		log.debug("getDepartments");

		return departmentService.getDepartments();
	}

	@GetMapping(value = "/departments")
	@ResponseBody
	@Cacheable(cacheNames = "departments", cacheManager = "cacheManager")
	public List<DepartmentTable> getDepartments() {
		log.debug("getDepartments");

		return departmentService.getDepartments();
	}

	@GetMapping(value = "/department/{deptNo}")
	@ResponseBody
	@Cacheable(cacheNames = "departments", key = "#deptNo", cacheManager = "cacheManager")
	public DepartmentTable getDepartment(@PathVariable String deptNo) {
		log.debug("getDepartment");

		return departmentService.getDepartment(deptNo);
	}
	
	@PutMapping(value = "/department")
	@ResponseBody
	@CacheEvict(cacheNames = "departments", allEntries = true, cacheManager = "cacheManager")
	@CachePut(cacheNames = "departments", key = "#departmentTable.deptNo", cacheManager = "cacheManager")
	public DepartmentTable setDepartments(@RequestBody DepartmentTable departmentTable) {
		log.debug("setDepartments");
		
		departmentService.setDepartment(departmentTable);
		
		return departmentTable;
	}

	@DeleteMapping(value = "/department/{deptNo}")
	@ResponseBody
	@CacheEvict(cacheNames = "departments", allEntries = true, cacheManager = "cacheManager")
	public void deleteDepartment(@PathVariable String deptNo) {
		log.debug("deleteDepartment");

		departmentService.deleteDepartment(deptNo);
	}
	
}
