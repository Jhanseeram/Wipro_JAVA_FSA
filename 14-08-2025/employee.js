var employees = [
    { empId: 1, empName: "Peter Parker", salary: 50000 },
    { empId: 2, empName: "Bruce Wayne", salary: 60000 },
    { empId: 3, empName: "Clark Kent", salary: 55000 }
];
function CountEmployees(list) {
    list.forEach(function (emp) {
        console.log("ID: ".concat(emp.empId, ", Name: ").concat(emp.empName, ", Salary: ").concat(emp.salary));
    });
    return list.length;
}
var count = CountEmployees(employees);
console.log("Total employees:", count);
