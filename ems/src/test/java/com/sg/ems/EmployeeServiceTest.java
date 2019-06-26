package com.sg.ems;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.sg.ems.exception.EntityNotFoundException;
import com.sg.ems.model.Employee;
import com.sg.ems.repository.EmployeeRepository;
import com.sg.ems.service.EmployeeService;
import com.sg.ems.service.impl.EmployeeServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTest {
	

	@Mock
	private EmployeeRepository employeeRepository;
	
	
	private EmployeeService employeeService;
	
	@Before
	public void setUp() throws Exception{
		
		employeeService = new EmployeeServiceImpl(employeeRepository);
	}

	
	@Test
	public void createEmployee_returnEmployee() {
		
		
		Employee employee = new Employee(1,"chandan","kumar","male",LocalDateTime.now(),"sse");

					
		when(employeeRepository.saveAndFlush(employee)).thenReturn(employee);

		Employee  savedEmployee = employeeService.createEmployee(employee);

		assertThat(savedEmployee.getFirstName()).isEqualTo("chandan");
		
	}
	
	
	@Test
	public void updateEmployee_returnEmployee() {
		
		
		Employee employee = new Employee(1,"chandan","singh","male",LocalDateTime.now(),"sse");

					
		when(employeeRepository.saveAndFlush(employee)).thenReturn(employee);
		
		when(employeeRepository.getOne(Mockito.anyInt())).thenReturn(employee);
		
		Employee savedEmployee = employeeService.createEmployee(employee);
		
		savedEmployee.setLastName("kumar");
		
		Employee  updatedEmployee = employeeService.updateEmployee(savedEmployee);

		assertThat(updatedEmployee.getLastName()).isEqualTo("kumar");
		
	}
	
	
	@Test(expected=EntityNotFoundException.class)
	public void deleteEmployee_returnVoid() {
		
		Employee employee = new Employee(1,"chandan","singh","male",LocalDateTime.now(),"sse");
		when(employeeRepository.getOne(Mockito.anyInt())).thenReturn(employee);
		
		 employeeService.deleteEmployee(1);		
		 employeeService.getEmployeeByName("chandan");
		
	}
	
	
	
	@Test
	public void getEmployeeByName_returnEmployee() {
		
		List<Employee> empList = new ArrayList<>();
		empList.add(new Employee(1,"chandan","kumar","male",LocalDateTime.now(),"sse"));
		empList.add(new Employee(2,"ravi","kumar","male",LocalDateTime.now(),"sse"));
					
		when(employeeRepository.findByFirstName("chandan")).thenReturn(empList);

		List<Employee>  employees = employeeService.getEmployeeByName("chandan");

		assertThat(employees).isNotEmpty();
		
	}
	
	
	@Test(expected=EntityNotFoundException.class)
	public void getEmployeeByName_NotFound() {
		
		List<Employee> empList = new ArrayList<>();
							
		when(employeeRepository.findByFirstName("chandan")).thenReturn(empList);

	    employeeService.getEmployeeByName("chandan");
		
	}
	
	@Test
	public void getAllEmployee_returnEmployees() {
		
		List<Employee> empList = new ArrayList<>();
		empList.add(new Employee(1,"chandan","kumar","male",LocalDateTime.now(),"sse"));
		empList.add(new Employee(2,"ravi","kumar","male",LocalDateTime.now(),"sse"));
					
		when(employeeRepository.findAll()).thenReturn(empList);

		List<Employee>  employees = employeeService.getAllEmployee();

		assertThat(employees).isNotEmpty();
		
	}

	
	
	
}
