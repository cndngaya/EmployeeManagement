package com.sg.ems.service.impl;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.sg.ems.exception.EntityNotFoundException;
import com.sg.ems.exception.EntityNullException;
import com.sg.ems.exception.IdNullException;
import com.sg.ems.model.Employee;
import com.sg.ems.repository.EmployeeRepository;
import com.sg.ems.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	
	private EmployeeRepository employeeRepository; 
	
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}
	@Override
	public Employee createEmployee(Employee employee) {
		
		if(employee == null)
			throw new EntityNullException("entity is null");
		
		Employee savedEmployee = employeeRepository.saveAndFlush(employee);
		
		return savedEmployee;
	}
	
	

	@Override
	public Employee updateEmployee(Employee employee) {
		
		if(employee == null)
			throw new EntityNullException("entity is null");
		
		if(employee.getId() == null)
			throw new IdNullException("id is null");
		
		Employee existingEmployee = employeeRepository.getOne(employee.getId());
		
		if(existingEmployee == null)
			throw new EntityNotFoundException("not found-"+employee.getId());
		
		
		existingEmployee.setFirstName(employee.getFirstName() != null ? employee.getFirstName():existingEmployee.getFirstName());
		existingEmployee.setLastName(employee.getLastName() != null ? employee.getLastName():existingEmployee.getLastName());
		existingEmployee.setGender(employee.getGender() != null ? employee.getGender():existingEmployee.getGender());
		existingEmployee.setDateOfBirth(employee.getDateOfBirth() != null ? employee.getDateOfBirth():existingEmployee.getDateOfBirth());
		existingEmployee.setDepartment(employee.getDepartment() != null ? employee.getDepartment():existingEmployee.getDepartment());	
		
		Employee updatedEmployee = employeeRepository.saveAndFlush(existingEmployee);
		return updatedEmployee;
	}

	@Cacheable("employees")
	@Override
	public List<Employee> getEmployeeByName(String name) {
		
		List<Employee> empList = employeeRepository.findByFirstName(name);
		
		if(empList == null)
			throw new EntityNotFoundException("not found-"+name);
		
		if(empList != null && empList.size()==0)
			throw new EntityNotFoundException("not found-"+name);
		
		List<Employee> sortedList = empList.stream().sorted((e1,e2)->e1.getFirstName().compareTo(e2.getFirstName())).collect(Collectors.toList());
		return sortedList;
	}

	@Override
	public List<Employee> getAllEmployee() {
	
		
		
       List<Employee> empList = employeeRepository.findAll();
		
       List<Employee> sortedList = null;
		if(empList != null && empList.size()>0) 
			 sortedList = empList.stream().sorted((e1,e2)->e1.getFirstName().compareTo(e2.getFirstName())).collect(Collectors.toList());
		
		return sortedList;
	}



	@Override
	public void deleteEmployee(Integer id) {
		
		Employee employee  = employeeRepository.getOne(id);
		
		if(employee == null)
			throw new EntityNotFoundException("not found-"+id);
		employeeRepository.delete(employee);
	}



	


	

}
