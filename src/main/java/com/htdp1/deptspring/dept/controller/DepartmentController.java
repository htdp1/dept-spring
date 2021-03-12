package com.htdp1.deptspring.dept.controller;

import java.util.List;

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
	public List<Department> getDepartments() {
		log.debug("getDepartments");

		return departmentService.getDepartments();
	}

	@GetMapping(value = "/department/{deptNo}")
	public Department getDepartment(@PathVariable String deptNo) {
		log.debug("getDepartment");

		return departmentService.getDepartment(deptNo);
	}

	@PutMapping(value = "/department")
	public int setDepartments(@RequestBody Department departmentTable) {
		log.debug("setDepartments");
		
		return departmentService.setDepartment(departmentTable);
	}

	@DeleteMapping(value = "/department/{deptNo}")
	public int deleteDepartment(@PathVariable String deptNo) {
		log.debug("deleteDepartment");

		return departmentService.deleteDepartment(deptNo);
	}

}
