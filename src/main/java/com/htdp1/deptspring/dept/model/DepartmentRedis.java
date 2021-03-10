package com.htdp1.deptspring.dept.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@RedisHash("htdp1:dept-spring:repository:departments")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class DepartmentRedis {
	@Id
	private String deptNo;
	private String deptName;

	@Builder
	public DepartmentRedis(String deptNo, String deptName) {
		this.deptNo = deptNo;
		this.deptName = deptName;
	}
}
