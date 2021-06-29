package com.bridgelabz;

import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;


import com.bridgelabz.EmployeePayrollService.IOService;

import java.time.LocalDate;
import java.util.ArrayList;

public class EmployeePayrollServiceTest {

	@Test
	public void givenEmployeePayrollDB_WhenRetrieved_ShouldMatchTotalEntries() {
		EmployeePayrollService employeePayrollService = new EmployeePayrollService();
		employeePayrollService.readEmployeeData(IOService.DB_IO);
		Assert.assertEquals(3, employeePayrollService.employeeDataSize());
	}

	@Test
	public void givenNewSalaryForEmployee_WhenUpdated_ShouldUpdateInTheDatabaseRecord() {
		EmployeePayrollService employeePayrollService = new EmployeePayrollService();
		employeePayrollService.readEmployeeData(IOService.DB_IO);
		try {
			employeePayrollService.updateEmployeeSalary("Terisa", 3000000.0);
			boolean result = employeePayrollService.isEmployeePayrollInSyncWithDB("Terisa");
			assertTrue(result);
		} catch (DatabaseException e) {
		}
	}


	@Test
	public void givenDateRange_WhenRetrievedFromDB_ShouldMatchTotalCount() {
		EmployeePayrollService employeePayrollService = new EmployeePayrollService();
		employeePayrollService.readEmployeeData(IOService.DB_IO);
		LocalDate startDate = LocalDate.of(2018, 01, 01);
		LocalDate endDate = LocalDate.now();
		ArrayList<EmployeePayrollData> list = employeePayrollService.readEmployeePayrollDataForDateRange(IOService.DB_IO, startDate, endDate);
		assertEquals(3, list.size());
	}
}