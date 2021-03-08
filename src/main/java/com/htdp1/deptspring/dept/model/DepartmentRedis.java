package com.htdp1.deptspring.dept.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.redis.core.RedisHash;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@RedisHash("htdp1:dest-spring:repository:departments")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Builder
public class DepartmentRedis {
	@Id
	private String deptNo;
	private String deptName;

	@PersistenceConstructor
	public DepartmentRedis(String deptNo, String deptName) {
		this.deptNo = deptNo;
		this.deptName = deptName;
	}
}
