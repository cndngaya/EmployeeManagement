package com.sg.ems.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sg.ems.exception.EntityNotFoundException;
import com.sg.ems.model.Employee;
import com.sg.ems.service.EmployeeService;
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class EmployeeController {

	
	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping("/employees")
	public ResponseEntity<Employee> createEmployee(@Valid @RequestBody Employee employee){		
		
        Employee savedEmployee = employeeService.createEmployee(employee);
		
		return new ResponseEntity<Employee>(savedEmployee,HttpStatus.CREATED);
	}
	
	
	@GetMapping("/employees/{name}")
	public List<Employee> getEmployeeByName(@PathVariable String name){
		return employeeService.getEmployeeByName(name);
	}
	
	
	@PutMapping("/employees")
	public  ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee){
		
		Employee updatedEmployee = employeeService.updateEmployee(employee);
		return new ResponseEntity<Employee>(updatedEmployee,HttpStatus.OK);
	}
	
	
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable Integer id){
		try {
		employeeService.deleteEmployee(id);
		}catch(EntityNotFoundException ex) {
			return new ResponseEntity<String>(ex.getMessage(),HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("Deleted Successfully...",HttpStatus.OK);
	}
	
	
	@GetMapping("/employees")
	public List<Employee> getAllEmployee(){
		return employeeService.getAllEmployee();
	}
	
	
	
	
}
