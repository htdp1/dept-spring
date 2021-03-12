package com.htdp1.deptspring.dept.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.htdp1.deptspring.dept.model.DepartmentRedis;

@RepositoryRestResource
public interface DepartmentRepositoryRedis extends CrudRepository<DepartmentRedis, String> {
}
