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

	@Cacheable(cacheNames = "departments", key = "'findAll'", cacheManager = "cacheManager")
	public List<Department> getDepartments() {
		return mapper.findAll();
	}

	@Cacheable(cacheNames = "departments", key = "#deptNo", cacheManager = "cacheManager")
	public Department getDepartment(String deptNo) {
		return mapper.findById(deptNo);
	}

	@CacheEvict(cacheNames = "departments", key = "'findAll'", cacheManager = "cacheManager")
	@CachePut(cacheNames = "departments", key = "#departmentTable.deptNo", cacheManager = "cacheManager")
	public int setDepartment(Department departmentTable) {
		return mapper.updateById(departmentTable);
	}

	@Caching(evict = { 
			@CacheEvict(cacheNames = "departments", key = "'findAll'", cacheManager = "cacheManager"),
			@CacheEvict(cacheNames = "departments", key = "#deptNo", cacheManager = "cacheManager") 
			}
	)
	public int deleteDepartment(String deptNo) {
		return mapper.deleteById(deptNo);
	}

}
