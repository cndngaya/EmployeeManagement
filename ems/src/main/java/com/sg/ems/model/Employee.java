package com.sg.ems.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

@Entity
@Table(name="employee")
public class Employee implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1634329899931549789L;
	private Integer id;
	private String firstName;
	private String lastName;
	private String gender;
	private LocalDateTime dateOfBirth;
	private String department;
	
	
	public Employee() {
		super();
	}
	

	public Employee(Integer id) {
		super();
		this.id = id;
	}

	public Employee(String firstName, String lastName, String gender, LocalDateTime dateOfBirth, String department) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.department = department;
	}
	
	public Employee(Integer id, String firstName, String lastName, String gender, LocalDateTime dateOfBirth,
			String department) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.department = department;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}

	@Size(max=15,min=2,message="FirstName must be greater than 2 characters")
	@Column(name="first_name")
	public String getFirstName() {
		return firstName;
	}


	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name="last_name")
	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	
	@Column(name="gender")
	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	@Past
	@Column(name="date_of_birth")
	public LocalDateTime getDateOfBirth() {
		return dateOfBirth;
	}


	public void setDateOfBirth(LocalDateTime dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@Column(name="department")
	public String getDepartment() {
		return department;
	}


	public void setDepartment(String department) {
		this.department = department;
	}
	
	
	
	
}
