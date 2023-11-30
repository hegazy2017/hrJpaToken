package com.hr.demo.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.hr.demo.security.User.entity.AppUser;

import lombok.Getter;
import lombok.Setter;

@Table(name = "employee")
@Entity
@NamedQuery(name = "Employee.findBySalary" , query = "select emp from Employee emp where emp.salary"
		+ " >= :salary and name like :name")

@Setter
@Getter
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "employee_id")
	private Long id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;
	
	private Date lastupdate;

	private Double salary;

	@ManyToOne()
	@JoinColumn(name = "department_id")
	private Department department;
	
	@OneToOne()
	@JoinColumn(name = "user_id")
	private AppUser user;
}
