package com.htdp1.deptspring.dept.repository;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.htdp1.deptspring.dept.model.Department;

@RepositoryRestResource
public interface DepartmentRepository extends CrudRepository<Department, String> {
	@Override
	@Cacheable(cacheNames = "departments", key = "'findAll'", cacheManager = "cacheManager")
	Iterable<Department> findAll();
}
