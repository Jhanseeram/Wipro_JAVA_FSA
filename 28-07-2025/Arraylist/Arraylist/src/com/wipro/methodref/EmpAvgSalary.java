package com.wipro.methodref;

import java.util.Arrays;
import java.util.List;

public class EmpAvgSalary {
	public static void main(String[] args) {
        List<EmployeeEx6> employees = Arrays.asList(
            new EmployeeEx6("John", 25, 40000),
            new EmployeeEx6("Alice", 35, 60000),
            new EmployeeEx6("Bob", 40, 70000),
            new EmployeeEx6("Charlie", 20, 30000),
            new EmployeeEx6("David", 38, 55000)
        );

        double averageSalary = employees.stream()
                .filter(e -> e.age > 30 && e.salary > 50000) 
                .mapToDouble(e -> e.salary)           
                .average()                               
                .orElse(0.0);                               

        double roundedAverage = Math.round(averageSalary * 100.0) / 100.0;

        System.out.println("Average Salary: " + roundedAverage);
    }
}
