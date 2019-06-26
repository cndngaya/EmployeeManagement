package com.sg.ems;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.sg.ems.controller.EmployeeController;
import com.sg.ems.exception.EntityNotFoundException;
import com.sg.ems.model.Employee;
import com.sg.ems.service.EmployeeService;

@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private EmployeeService employeeServiceMock;
	
	
	@Test
	public void testCreateEmployee_Created() throws Exception{
		
		String employeeJson = "{\"id\":2,\"firstName\":\"ravi\",\"lastName\":\"kumar\",\"gender\":\"male\",\"dateOfBirth\":\"2019-06-23T12:24:09.033219\",\"department\":\"sse\"}";

		Employee employee = new Employee(2, "ravi", "kumar", "male", LocalDateTime.now(), "sse");

		when(employeeServiceMock.createEmployee(Mockito.any(Employee.class))).thenReturn(employee);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/employees").accept(MediaType.APPLICATION_JSON)
				.content(employeeJson).contentType(MediaType.APPLICATION_JSON);

		mockMvc.perform(requestBuilder).andExpect(status().isCreated());
	   		

	}
	
	
	
	@Test
	public void testUpdateEmployee_updated() throws Exception{
		
		String employeeJson = "{\"id\":2,\"firstName\":\"ravi\",\"lastName\":\"kumar\",\"gender\":\"male\",\"dateOfBirth\":\"2019-06-23T12:24:09.033219\",\"department\":\"sse\"}";

		Employee employee = new Employee(2, "ravi", "singh", "male", LocalDateTime.now(), "sse");

       when(employeeServiceMock.updateEmployee(Mockito.any(Employee.class))).thenReturn(employee);

		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/api/employees")
                .accept(MediaType.APPLICATION_JSON)
                .content(employeeJson)
                .contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		
		MockHttpServletResponse response = mvcResult.getResponse();
		
		assertThat(response.getContentAsString().contains("singh"));


	}
	
	
	
	@Test
	public void testDeleteEmployee_deleted() throws Exception{
		                                     

	     mockMvc.perform(MockMvcRequestBuilders.delete("/api/employees/1")
                 .accept(MediaType.APPLICATION_JSON))
	             .andExpect(status().isOk());
		
		verify(employeeServiceMock).deleteEmployee(1);


	}
	
	
	@Test
	public void testGetEmployeeByName_Found() throws Exception{
		
		List<Employee> empList = new ArrayList<Employee>();
		empList.add(new Employee(1,"chandan","kumar","male",LocalDateTime.now(),"sse"));
		empList.add(new Employee(2,"chandan","singh","male",LocalDateTime.now(),"sse"));
		
		when(employeeServiceMock.getEmployeeByName(anyString())).thenReturn(empList);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/employees/chandan"))
		.andExpect(status().isOk())
        .andExpect(jsonPath("$[0].id", is(1)))
        .andExpect(jsonPath("$[0].firstName", is("chandan")))
        .andExpect(jsonPath("$[0].lastName", is("kumar")))
        .andExpect(jsonPath("$[1].id", is(2)))
        .andExpect(jsonPath("$[1].firstName", is("chandan")))
        .andExpect(jsonPath("$[1].lastName", is("singh")));
       
	}
	
	
	@Test
	public void testGetEmployeeByName_NotFound() throws Exception{
		
		when(employeeServiceMock.getEmployeeByName(anyString())).thenThrow(new EntityNotFoundException("not found-chandan"));
		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/employees/chandan"))
		.andExpect(status().isNotFound());
       
	}
	
	
	
	@Test
	public void testGetAllEmployee_isNotEmpty() throws Exception{
		
		List<Employee> empList = Arrays.asList(new Employee(2,"ravi","kumar","male",LocalDateTime.now(),"sse"),new Employee(1,"chandan","kumar","male",LocalDateTime.now(),"sse"));
		
		when(employeeServiceMock.getAllEmployee()).thenReturn(empList);

       mockMvc.perform(MockMvcRequestBuilders.get("/api/employees"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$", hasSize(2)));
       
	}
	
	
	
	@Test
	public void testGetAllEmployee_isEmpty() throws Exception{
		
		List<Employee> empList = Arrays.asList();
		
		when(employeeServiceMock.getAllEmployee()).thenReturn(empList);

       mockMvc.perform(MockMvcRequestBuilders.get("/api/employees"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$", hasSize(0)));
	}
	
}
