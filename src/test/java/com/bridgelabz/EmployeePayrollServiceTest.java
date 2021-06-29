package com.bridgelabz;

import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;


import com.bridgelabz.EmployeePayrollService.IOService;

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
		} catch (DatabaseException e) {}
	}
}