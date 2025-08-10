package com.Wipro.oops;

import java.util.*;
import java.util.ArrayList;

class Employee implements Comparable<Employee> {
    private String empId;
    private String empName;
    private int empAge;
    private double empSalary;

    public Employee(String empId, String empName, int empAge, double empSalary) {
        this.empId = empId;
        this.empName = empName;
        this.empAge = empAge;
        this.empSalary = empSalary;
    }

    public double getEmpSalary() {
        return empSalary;
    }

    @Override
    public String toString() {
        return "Employee [ID=" + empId + ", Name=" + empName + ", Age=" + empAge + ", Salary=" + empSalary + "]";
    }

    @Override
    public int compareTo(Employee other) {
        
        if (this.empSalary < other.empSalary) 
        {
            return 1; 
        }
        else if (this.empSalary > other.empSalary) 
        {
            return -1; 
        }
        else 
        {
            return 0;
        }
    }
}

public class EmployeeSortUsingComparable {
    public static void main(String[] args) {
        // Step 1: Create List of Employees
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee("001", "Soham", 21, 50000));
        employeeList.add(new Employee("002", "Pratham", 28, 70000));
        employeeList.add(new Employee("003", "Kshitij", 35, 60000));
        employeeList.add(new Employee("004", "Harsh", 32, 55000));

        Collections.sort(employeeList);

        System.out.println("Sorting by Salary (Descending):");
        for (Employee emp : employeeList) {
            System.out.println(emp);
        }
    }
}
