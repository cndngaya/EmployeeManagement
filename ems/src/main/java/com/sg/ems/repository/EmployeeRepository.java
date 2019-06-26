package com.sg.ems.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sg.ems.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

	public List<Employee> findByFirstName(String firstName);
}
