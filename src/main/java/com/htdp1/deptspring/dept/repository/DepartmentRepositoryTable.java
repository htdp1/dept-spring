package com.htdp1.deptspring.dept.repository;

import org.springframework.data.repository.CrudRepository;

import com.htdp1.deptspring.dept.model.DepartmentTable;

public interface DepartmentRepositoryTable extends CrudRepository<DepartmentTable, String> {
}
