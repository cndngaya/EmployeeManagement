package com.sg.ems.service;

import java.util.List;

import com.sg.ems.model.Employee;

public interface EmployeeService {
	
	
	public List<Employee> getEmployeeByName(String name);
	
	public List<Employee> getAllEmployee();

	public Employee createEmployee(Employee employee);

	public Employee updateEmployee(Employee employee);

	public void deleteEmployee(Integer id);
}
