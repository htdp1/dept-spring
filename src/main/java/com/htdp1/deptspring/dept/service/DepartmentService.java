package com.htdp1.deptspring.dept.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.htdp1.deptspring.dept.mapper.DepartmentMapper;
import com.htdp1.deptspring.dept.model.Department;

@Service
public class DepartmentService {
	@Autowired
	public DepartmentMapper mapper;

	public List<Department> getDepartment() {
		return mapper.findAll();
	}

	public Department selectDepartment(String deptNo) {
		return mapper.findById(deptNo);
	}
}
