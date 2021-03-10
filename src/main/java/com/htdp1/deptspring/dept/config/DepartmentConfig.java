package com.htdp1.deptspring.dept.config;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.relational.core.mapping.event.BeforeSaveEvent;

import com.htdp1.deptspring.dept.model.DepartmentTable;

import lombok.AllArgsConstructor;

@Configuration
public class DepartmentConfig {

	@Bean
	@SuppressWarnings("rawtypes")
	public ApplicationListener<BeforeSaveEvent> idSetting() {

		return event -> {

			if (event.getEntity() instanceof DepartmentTable) {
				double dValue = Math.random();

				DepartmentTable departmentTable = (DepartmentTable) event.getEntity();
				if (departmentTable.getDeptNo() == null) {
					departmentTable.setDeptNo("t" + dValue);
				}
			}
		};
	}
}
