package com.htdp1.deptspring.dept.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table("departments")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class DepartmentTable {
	@Id
	private String deptNo;
	private String deptName;

	@Builder
	public DepartmentTable(String deptNo, String deptName) {
		this.deptNo = deptNo;
		this.deptName = deptName;
	}
}
