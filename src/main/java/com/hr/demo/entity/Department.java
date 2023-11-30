package com.hr.demo.entity;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "departments")
public class Department {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "depratment_gen")
//	@SequenceGenerator(name = "depratment_gen" , sequenceName = "department_seq", initialValue = 100)
//	
//	@GeneratedValue(strategy = GenerationType.TABLE, generator = "depratment_gen")
//	@TableGenerator(name = "depratment_gen", table = "depratment_seq", allocationSize = 1, initialValue = 20)
	private Long id;
	
	private String name;
	
	@OneToMany(mappedBy = "department" , fetch = FetchType.LAZY)
 	private List<Employee> employees;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



}