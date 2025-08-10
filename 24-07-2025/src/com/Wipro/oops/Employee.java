package com.Wipro.oops;

import java.util.*;

public class Employee {
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

    public String getEmpId() {
        return empId;
    }

    public String getEmpName() {
        return empName;
    }

    public int getEmpAge() {
        return empAge;
    }

    @Override
    public String toString() {
        return "Employee [ID=" + empId + ", Name=" + empName + ", Age=" + empAge + ", Salary=" + empSalary + "]";
    }
}

// Comparator to sort by empId
class SortByEmpId implements Comparator<Employee> {
    public int compare(Employee e1, Employee e2) {
        return e1.getEmpId().compareTo(e2.getEmpId());
    }
}

// Comparator to sort by empName
class SortByEmpName implements Comparator<Employee> {
    public int compare(Employee e1, Employee e2) {
        return e1.getEmpName().compareTo(e2.getEmpName());
    }
}

// Comparator to sort by empAge
class SortByEmpAge implements Comparator<Employee> {
    public int compare(Employee e1, Employee e2) {
        return Integer.compare(e1.getEmpAge(), e2.getEmpAge());
    }
}

