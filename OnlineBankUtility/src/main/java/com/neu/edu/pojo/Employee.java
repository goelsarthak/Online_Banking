package com.neu.edu.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.Size;

@Entity
public class Employee {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int EmployeeId;
	@Size(min=5, max=10, message="Passoword length should be min and max 10")
	@Column(name ="pwd", nullable=false)
	private String password;
	@OneToOne
	@JoinColumn(name="RoleId", nullable = false)
	private Role role;
	@Column(name="EmailID",  unique=true, nullable=false)
	private String emailId;
	@Column(name="Phone", nullable=false)
	private String phone;
	@Column(name="EmpName", nullable=false)
	private String empName;
	
	public int getEmployeeId() {
		return EmployeeId;
	}
	
	public void setEmployeeId(int employeeId) {
		EmployeeId = employeeId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	
}
