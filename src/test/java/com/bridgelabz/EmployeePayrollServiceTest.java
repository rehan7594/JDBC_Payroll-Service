package com.bridgelabz;

import org.junit.Assert;
import org.junit.Test;

public class EmployeePayrollServiceTest {

	@Test
	public void givenEmployeePayrollDB_WhenRetrieved_ShouldMatchTotalEntries() {
		EmployeePayrollService employeePayrollService = new EmployeePayrollService();
		employeePayrollService.readEmployeeData(EmployeePayrollService.IOService.DB_IO);
		Assert.assertEquals(3, employeePayrollService.employeeDataSize());
	}
}