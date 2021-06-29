package com.bridgelabz;

import java.util.ArrayList;

public class EmployeePayrollService {

	public enum IOService {
		CONSOLE_IO, FILE_IO, DB_IO, REST_IO
	}

	private ArrayList<EmployeePayrollData> employeePayrollDataList;
	public ArrayList<EmployeePayrollData> readEmployeeData(IOService ioService) {
		if(ioService.equals(IOService.DB_IO)) {
			try {
				this.employeePayrollDataList = new EmployeePayrollDBService().readEmployeeData();
			} catch (DatabaseException e) {
				System.out.println(e.getMessage());
			}
		}
		return employeePayrollDataList;
	}

	public long employeeDataSize() {
		return employeePayrollDataList.size();
	}
}