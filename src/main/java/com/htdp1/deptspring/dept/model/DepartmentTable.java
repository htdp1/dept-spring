package com.htdp1.deptspring.dept.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table("departments")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Builder
public class DepartmentTable {
	@Id
	private String deptNo;
	private String deptName;

	@PersistenceConstructor
	public DepartmentTable(String deptNo, String deptName) {
		this.deptNo = deptNo;
		this.deptName = deptName;
	}
}
