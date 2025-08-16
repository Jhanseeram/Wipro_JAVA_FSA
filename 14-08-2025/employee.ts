interface Employee {
    empId: number;
    empName: string;
    salary: number;
}

let employees: Employee[] = [
    { empId: 1, empName: "Peter Parker", salary: 50000 },
    { empId: 2, empName: "Bruce Wayne", salary: 60000 },
    { empId: 3, empName: "Clark Kent", salary: 55000 }
];

function CountEmployees(list: Employee[]): number {
    list.forEach(emp => {
        console.log(`ID: ${emp.empId}, Name: ${emp.empName}, Salary: ${emp.salary}`);
    });
    return list.length;
}

const count = CountEmployees(employees);
console.log("Total employees:", count);