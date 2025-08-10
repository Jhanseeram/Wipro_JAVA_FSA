package com.wipro.springdemo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wipro.springdemo.beans.Department;

public class App {

	public static void main(String[] args) {
	  @SuppressWarnings("resource")
	  ApplicationContext ctxt= new ClassPathXmlApplicationContext("beans.xml");
      Department department= ctxt.getBean(Department.class);
      department.setDeptCode("Fin");
      department.setDepartmentName("Finance");
      System.out.println(department);    

	}

}
