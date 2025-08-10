package com.wipro.springdemo.beans;

public class College {
	String collegeName;
	Department department;
	
	
	
	public College(String collegeName, Department department) {
		super();
		this.collegeName = collegeName;
		this.department = department;
	}
	
	public String getCollegeName() {
		return collegeName;
	}
	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	@Override
	public String toString() {
		return "College [collegeName=" + collegeName + ", department=" + department + "]";
	}
	
	
}
