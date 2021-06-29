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
}