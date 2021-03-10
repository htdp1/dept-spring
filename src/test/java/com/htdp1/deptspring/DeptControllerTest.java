package com.htdp1.deptspring;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.htdp1.deptspring.dept.model.DepartmentTable;

@SpringBootTest
@AutoConfigureMockMvc
public class DeptControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	private static ObjectMapper objectMapper = new ObjectMapper();
	
	final DepartmentTable departmentTable = DepartmentTable.builder().deptNo("t001").deptName("test001").build();

	@Test 
	public void getDepartment() throws Exception { // when // then 
		String param = objectMapper.writeValueAsString(departmentTable);
		
		this.mockMvc.perform(put("/v1/department")
				.contentType(MediaType.APPLICATION_JSON)
				.content(param))
				.andExpect(status().isOk())
				.andDo(print());
		
		this.mockMvc.perform(get("/v1/departments").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(print());
	}
}
