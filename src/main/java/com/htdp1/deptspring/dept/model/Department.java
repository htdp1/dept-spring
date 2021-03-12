package com.htdp1.deptspring.dept.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "departments")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class Department {
	@Id
	@Column(name = "dept_no")
	private String deptNo;
	@Column(name = "dept_name")
	private String deptName;

	@Builder
	public Department(String deptNo, String deptName) {
		this.deptNo = deptNo;
		this.deptName = deptName;
	}
}
