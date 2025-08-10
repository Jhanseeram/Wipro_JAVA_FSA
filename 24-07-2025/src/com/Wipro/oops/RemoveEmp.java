package com.Wipro.oops;

import java.util.concurrent.CopyOnWriteArrayList;

public class RemoveEmp {
    public static void main(String[] args) {
        
        CopyOnWriteArrayList<Employee> employeeList = new CopyOnWriteArrayList<>();

        employeeList.add(new Employee("001", "Soham", 22, 50000));
        employeeList.add(new Employee("002", "Pratham", 28, 90000));
        employeeList.add(new Employee("003", "Kshitij", 35, 85000));
        employeeList.add(new Employee("004", "Harsh", 32, 75000));

        for (Employee emp : employeeList) {
            if (emp.getEmpSalary() > 80000) {
                employeeList.remove(emp);
            }
        }
        
        System.out.println("Salary < 80000):");
        for (Employee emp : employeeList) {
            System.out.println(emp);
        }
    }
}
