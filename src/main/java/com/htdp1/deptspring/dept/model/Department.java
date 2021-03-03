package com.htdp1.deptspring.dept.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import lombok.Builder;
import lombok.Data;

@Data
@RedisHash("departments")
public class Department {
	@Id
	private String deptNo;
	private String deptName;

	@Builder
	public Department(String deptNo, String deptName) {
		this.deptNo = deptNo;
		this.deptName = deptName;
	}
}
