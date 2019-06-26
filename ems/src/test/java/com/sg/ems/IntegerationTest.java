package com.sg.ems;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import com.sg.ems.model.Employee;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class IntegerationTest {

	@Autowired
	private TestRestTemplate restTemplate;

	  @Test
	  @SuppressWarnings("rawtypes")
	  public void test_getAllEmployee() {
		  
		  
	   
        List<Employee> empList = new ArrayList<Employee>();
        empList.add(new Employee(1,"chandan","kumar","male",LocalDateTime.now(),"sse"));
        
		HttpEntity<List<Employee>> requestEntity = new HttpEntity<List<Employee>>(empList);
		
		
		ResponseEntity<List> response = restTemplate.exchange("/api/employees", HttpMethod.GET, requestEntity,List.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	  }
	  
		
	  @Test
	  @SuppressWarnings("rawtypes")
	  public void test_getEmployeeByName() throws Exception{
	    
		    List<Employee> empList = new ArrayList<Employee>();
	        empList.add(new Employee(1,"chandan","kumar","male",LocalDateTime.now(),"sse"));
	        
			HttpEntity<List<Employee>> requestEntity = new HttpEntity<List<Employee>>(empList);
			ResponseEntity<List> response = restTemplate.exchange("/api/employees/chandan", HttpMethod.GET, requestEntity, List.class);
	        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	  }

		
	  @Test
	  public void test_createEmployee() throws Exception{
	   
	    Employee employee = new Employee(1,"chandan","kumar","male",LocalDateTime.now(),"sse");

	    ResponseEntity<Employee> responseEntity = restTemplate.postForEntity("/api/employees",employee, Employee.class);
	    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
	  }

	  @Test
	  public void test_updateEmployee() throws Exception{

	    restTemplate.put("/gs/employees",new Employee(1,"chandan","singh","male",LocalDateTime.now(),"sse"));

	    ResponseEntity<Employee> responseEntity = restTemplate.getForEntity("/api/employees/1", Employee.class);
	    
        responseEntity.getStatusCode();
		assertThat(HttpStatus.OK);
	  }

	  @Test
	  public void test_deleteEmployee() throws Exception{     

	    try {
	    	restTemplate.delete("/api/employees/10");
	    } catch (final HttpClientErrorException e) {
	    	assertThat(e.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
	    }
	  }
		    	 
}
