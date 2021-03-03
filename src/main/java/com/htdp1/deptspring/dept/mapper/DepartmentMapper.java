package com.htdp1.deptspring.dept.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.htdp1.deptspring.dept.model.Department;

@Mapper
public interface DepartmentMapper {
	List<Department> findAll();

	Department findById(String deptNo);
}
