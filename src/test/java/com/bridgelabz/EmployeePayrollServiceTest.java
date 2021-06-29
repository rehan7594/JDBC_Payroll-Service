package com.bridgelabz;

import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;


import com.bridgelabz.EmployeePayrollService.IOService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;

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

    @Test
    public void givenPayrollDB_WhenSumOfSalaryRetrievedByGender_ShouldReturnCorrectResult() {
        EmployeePayrollService employeePayrollService = new EmployeePayrollService();
        employeePayrollService.readEmployeeData(IOService.DB_IO);
        Map<String, Double> sumOfSalariesByGender = employeePayrollService.readSumOfSalariesByGender(IOService.DB_IO);
        try {
        assertTrue(sumOfSalariesByGender.get("M").equals(4000000.00) && sumOfSalariesByGender.get("F").equals(3000000.00));
        }catch (Exception e){ }
    }

    @Test
    public void givenPayrollDB_WhenAverageSalaryRetrievedByGender_ShouldReturnCorrectResult() {
        EmployeePayrollService employeePayrollService = new EmployeePayrollService();
        employeePayrollService.readEmployeeData(IOService.DB_IO);
        Map<String, Double> avgSalaryByGender = employeePayrollService.readAverageSalaryByGender(IOService.DB_IO);
        try {
            assertTrue(avgSalaryByGender.get("M").equals(2000000.00) && avgSalaryByGender.get("F").equals(3000000.00));
        }catch (Exception e){ }
        }

    @Test
    public void givenPayrollDB_WhenMinimumSalaryRetrievedByGender_ShouldReturnCorrectResult() {
        EmployeePayrollService employeePayrollService = new EmployeePayrollService();
        employeePayrollService.readEmployeeData(IOService.DB_IO);
        Map<String, Double> minSalaryByGender = employeePayrollService.readMinSalaryByGender(IOService.DB_IO);
       try {
           assertTrue(minSalaryByGender.get("M").equals(1000000.00) && minSalaryByGender.get("F").equals(3000000.00));
       }catch (Exception e){ }
       }

    @Test
    public void givenPayrollDB_WhenMaximumSalaryRetrievedByGender_ShouldReturnCorrectResult() {
        EmployeePayrollService employeePayrollService = new EmployeePayrollService();
        employeePayrollService.readEmployeeData(IOService.DB_IO);
        Map<String, Double> maxSalaryByGender = employeePayrollService.readMaxSalaryByGender(IOService.DB_IO);
        try {
            assertTrue(maxSalaryByGender.get("M").equals(3000000.00) && maxSalaryByGender.get("F").equals(3000000.00));
        }catch (Exception e){ }
    }

    @Test
    public void givenPayrollDB_WhenEmployeeCountRetrievedByGender_ShouldReturnCorrectResult() {
        EmployeePayrollService employeePayrollService = new EmployeePayrollService();
        employeePayrollService.readEmployeeData(IOService.DB_IO);
        Map<String, Integer> countByGender = employeePayrollService.readEmployeeCountByGender(IOService.DB_IO);
       try {
           assertTrue(countByGender.get("M").equals(2) && countByGender.get("F").equals(1));
       }catch (Exception e){ }
       }
}