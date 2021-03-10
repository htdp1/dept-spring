package com.htdp1.deptspring.dept.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.htdp1.deptspring.dept.model.DepartmentTable;

@Mapper
public interface DepartmentMapper {
	List<DepartmentTable> findAll();

	DepartmentTable findById(String deptNo);
	
	int updateById(DepartmentTable departmentTable);

	int deleteById(String deptNo);
}
