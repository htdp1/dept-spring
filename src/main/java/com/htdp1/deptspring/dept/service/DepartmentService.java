package com.htdp1.deptspring.dept.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.htdp1.deptspring.dept.mapper.DepartmentMapper;
import com.htdp1.deptspring.dept.model.DepartmentTable;

@Service
public class DepartmentService {
	@Autowired
	public DepartmentMapper mapper;

	public List<DepartmentTable> getDepartments() {
		return mapper.findAll();
	}

	public DepartmentTable getDepartment(String deptNo) {
		return mapper.findById(deptNo);
	}
	
	public void setDepartment(DepartmentTable departmentTable) {
		mapper.updateById(departmentTable);
	}
	
	public void deleteDepartment(String deptNo) {
		mapper.deleteById(deptNo);
	}

}
