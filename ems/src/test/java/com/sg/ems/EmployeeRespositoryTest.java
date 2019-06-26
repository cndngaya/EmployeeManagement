package com.sg.ems;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.sg.ems.model.Employee;
import com.sg.ems.repository.EmployeeRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class EmployeeRespositoryTest {
	
	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private TestEntityManager entityManager;
	
	
	
	@Test
	public void save() throws Exception {
		
		Employee emp = new Employee("raju","kumar","male",LocalDateTime.now(),"sse");

		Employee savedEmployee = entityManager.persistAndFlush(emp);
		
		assertThat(savedEmployee.getId()).isNotNull().isNotNegative();
		
	}
	
	@Test(expected=Exception.class)
	public void delete() throws Exception {
		
		Employee emp = new Employee("raju","kumar","male",LocalDateTime.now(),"sse");

		Employee savedEmployee = entityManager.persistAndFlush(emp);
		
		assertThat(savedEmployee.getId()).isNotNull().isNotNegative();
		
		employeeRepository.delete(savedEmployee);
		employeeRepository.getOne(savedEmployee.getId());		
	}

	@Test
	public void findByFirstName() throws Exception {
		
		Employee emp = new Employee("raju","kumar","male",LocalDateTime.now(),"sse");

		Employee savedEmployee = entityManager.persistAndFlush(emp);
		
		assertThat(savedEmployee.getId()).isNotNull().isNotNegative();
		
		List<Employee> employees = employeeRepository.findByFirstName("chandan");

		assertThat(employees).isNotEmpty();

	}
	
	@Test
	public void findAll()throws Exception  {
		
		Employee emp = new Employee("raju","kumar","male",LocalDateTime.now(),"sse");

		Employee savedEmployee = entityManager.persistAndFlush(emp);
		
		assertThat(savedEmployee.getId()).isNotNull().isNotNegative();
		
		List<Employee> employees = employeeRepository.findAll();

		assertThat(employees).isNotEmpty();

	}

}
