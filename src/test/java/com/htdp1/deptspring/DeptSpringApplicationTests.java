package com.htdp1.deptspring;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.htdp1.deptspring.dept.model.DepartmentRedis;
import com.htdp1.deptspring.dept.repository.DepartmentRepositoryRedis;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class DeptSpringApplicationTests {

	@Autowired
	private DepartmentRepositoryRedis departmentRepository;

	@Test
	public void basicSave() {
		departmentRepository.deleteAll();

		for (int i = 0; i < 100; i++) {
			departmentRepository.save(new DepartmentRedis("t" + i, "test" + i));
		}

		log.debug("count => " + departmentRepository.count());

		// given
		DepartmentRedis department = new DepartmentRedis("t0001", "test1");
		// when
		DepartmentRedis savedDepartment = departmentRepository.save(department);
		// then
		Optional<DepartmentRedis> findDepartment = departmentRepository.findById(savedDepartment.getDeptNo());

		assertThat(findDepartment.isPresent()).isEqualTo(Boolean.TRUE);
		assertThat(findDepartment.get().getDeptName()).isEqualTo(department.getDeptName());

		log.debug("findDepartment => " + findDepartment);
		log.debug("Test end.");
	}

}
