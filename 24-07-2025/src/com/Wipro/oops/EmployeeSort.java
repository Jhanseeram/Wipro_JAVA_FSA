package com.Wipro.oops;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class EmployeeSort {
	
	public static void main(String[] args) {
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee("E003", "Charlie", 35, 60000));
        employeeList.add(new Employee("E001", "Alice", 30, 50000));
        employeeList.add(new Employee("E004", "David", 32, 55000));
        employeeList.add(new Employee("E002", "Bob", 28, 70000));

        System.out.println("Original List:");
        for (Employee emp : employeeList) {
            System.out.println(emp);
        }

        Collections.sort(employeeList, new SortByEmpId());
        System.out.println("\nSorted by empId:");
        for (Employee emp : employeeList) {
            System.out.println(emp);
        }

        // Sort by empName
        Collections.sort(employeeList, new SortByEmpName());
        System.out.println("\nSorted by empName:");
        for (Employee emp : employeeList) {
            System.out.println(emp);
        }

        // Sort by empAge
        Collections.sort(employeeList, new SortByEmpAge());
        System.out.println("\nSorted by empAge:");
        for (Employee emp : employeeList) {
            System.out.println(emp);
        }
    }
}
