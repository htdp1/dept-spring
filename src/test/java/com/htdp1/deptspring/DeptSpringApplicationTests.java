package com.htdp1.deptspring;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.htdp1.deptspring.dept.model.Department;
import com.htdp1.deptspring.dept.repository.DepartmentRepository;

@SpringBootTest
class DeptSpringApplicationTests {
	
	@Autowired
	private DepartmentRepository departmentRepository;

	@Test
	public void basicSave() {
		// given
		Department department = new Department("t0001", "test1");
		// when
		Department savedDepartment = departmentRepository.save(department);
		// then
		Optional<Department> findDepartment = departmentRepository.findById(savedDepartment.getDeptNo());

		assertThat(findDepartment.isPresent()).isEqualTo(Boolean.TRUE);
		assertThat(findDepartment.get().getDeptName()).isEqualTo(department.getDeptName());
	}

}
