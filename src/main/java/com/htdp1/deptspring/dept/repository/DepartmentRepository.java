package com.htdp1.deptspring.dept.repository;

import java.util.Optional;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.htdp1.deptspring.dept.model.Department;

@RepositoryRestResource
public interface DepartmentRepository extends CrudRepository<Department, String> {
	@Override
	@Cacheable(cacheNames = "departments", key = "'findAll'", cacheManager = "cacheManager")
	Iterable<Department> findAll();

	@Cacheable(cacheNames = "departments", key = "#deptNo", cacheManager = "cacheManager")
	Optional<Department> findById(String deptNo);

	@CachePut(cacheNames = "departments", key = "#department.deptNo", cacheManager = "cacheManager")
	@CacheEvict(cacheNames = "departments", key = "'findAll'", cacheManager = "cacheManager")
	<S extends Department> S save(Department department);

	@Caching(evict = { 
			@CacheEvict(cacheNames = "departments", key = "'findAll'", cacheManager = "cacheManager"),
			@CacheEvict(cacheNames = "departments", key = "#deptNo", cacheManager = "cacheManager") 
			}
	)
	void deleteById(String deptNo);
}
