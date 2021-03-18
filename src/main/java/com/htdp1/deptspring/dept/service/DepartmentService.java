package com.htdp1.deptspring.dept.service;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import com.htdp1.deptspring.dept.mapper.DepartmentMapper;
import com.htdp1.deptspring.dept.model.Department;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DepartmentService {

	public DepartmentMapper mapper;

	public List<Department> getDepartments() {
		return mapper.findAll();
	}

	public Department getDepartment(String deptNo) {
		return mapper.findById(deptNo);
	}

	public int setDepartment(Department departmentTable) {
		return mapper.updateById(departmentTable);
	}

	public int deleteDepartment(String deptNo) {
		return mapper.deleteById(deptNo);
	}

}
