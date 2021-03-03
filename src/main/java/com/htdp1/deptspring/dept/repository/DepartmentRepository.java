package com.htdp1.deptspring.dept.repository;

import org.springframework.data.repository.CrudRepository;

import com.htdp1.deptspring.dept.model.Department;

public interface DepartmentRepository extends CrudRepository<Department, String> {
}
