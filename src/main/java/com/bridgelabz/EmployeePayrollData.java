package com.bridgelabz;

import java.time.LocalDate;

public class EmployeePayrollData {

    private int empId;
    private String empName;
    private double salary;
    private LocalDate startDate;

    public EmployeePayrollData(int empId, String empName, double salary) {
        this.empName = empName;
        this.empId = empId;
        this.salary = salary;
    }

    public EmployeePayrollData(int id, String name, double salary, LocalDate startDate) {
        this(id, name, salary);
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return "Id: " + empId + " Name: " + empName + " Salary: " + salary;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public long getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        EmployeePayrollData other = (EmployeePayrollData) obj;

        return empId == other.empId && empName.equals(other.empName) && Double.compare(salary, other.salary) == 0;
    }
}