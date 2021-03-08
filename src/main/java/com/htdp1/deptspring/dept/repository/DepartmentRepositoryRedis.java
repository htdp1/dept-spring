package com.htdp1.deptspring.dept.repository;

import org.springframework.data.repository.CrudRepository;

import com.htdp1.deptspring.dept.model.DepartmentRedis;

public interface DepartmentRepositoryRedis extends CrudRepository<DepartmentRedis, String> {
}
