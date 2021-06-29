package com.bridgelabz;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;

public class EmployeePayrollService {

    public enum IOService {
        CONSOLE_IO, FILE_IO, DB_IO, REST_IO
    }

    private ArrayList<EmployeePayrollData> employeePayrollDataList;
    private EmployeePayrollDBService employeePayrollDBService;



    public EmployeePayrollService() {
        employeePayrollDBService = EmployeePayrollDBService.createInstance();
        this.employeePayrollDataList = new ArrayList<EmployeePayrollData>();
    }

    public EmployeePayrollService(ArrayList<EmployeePayrollData> employeePayrollDataList) {
        employeePayrollDBService = EmployeePayrollDBService.createInstance();
        this.employeePayrollDataList = employeePayrollDataList;
    }

    public ArrayList<EmployeePayrollData> readEmployeeData(IOService ioService) {
        if(ioService.equals(IOService.DB_IO)) {
            try {
                this.employeePayrollDataList = employeePayrollDBService.readEmployeeData();
            } catch (DatabaseException e) {
                System.out.println(e.getMessage());
            }
        }
        return employeePayrollDataList;
    }

    /**
     * @param service
     * @param startDate
     * @param endDate
     * @return List of Employee Payroll data between given date range
     */
    public ArrayList<EmployeePayrollData> readEmployeePayrollDataForDateRange(IOService service, LocalDate startDate, LocalDate endDate) {
        if(service.equals(IOService.DB_IO)) {
            try {
                return employeePayrollDBService.getEmployeeDataForDateRange(startDate, endDate);
            } catch (DatabaseException e) {
                System.out.println(e.getMessage());
            }
        }
        return null;
    }

    /**
     * @param service
     * @return Map containing gender as key and sum of salaries as value
     */
    public Map<String, Double> readSumOfSalariesByGender(IOService service) {
        if(service.equals(IOService.DB_IO)) {
            try {
                return employeePayrollDBService.getSumOfSalariesByGender();
            } catch (DatabaseException e) {
                System.out.println(e.getMessage());
            }
        }
        return null;
    }

    /**
     * @param service
     * @return Map containing gender as key and avg salary as value
     */
    public Map<String, Double> readAverageSalaryByGender(IOService service) {
        if(service.equals(IOService.DB_IO)) {
            try {
                return employeePayrollDBService.getAvgSalaryByGender();
            } catch (DatabaseException e) {
                System.out.println(e.getMessage());
            }
        }
        return null;
    }

    /**
     * @param service
     * @return Map containing gender as key and avg salary as value
     */
    public Map<String, Double> readMinSalaryByGender(IOService service) {
        if(service.equals(IOService.DB_IO)) {
            try {
                return employeePayrollDBService.getMinSalaryByGender();
            } catch (DatabaseException e) {
                System.out.println(e.getMessage());
            }
        }
        return null;
    }

    /**
     * @param service
     * @return Map containing gender as key and max as value
     */
    public Map<String, Double> readMaxSalaryByGender(IOService service) {
        if(service.equals(IOService.DB_IO)) {
            try {
                return employeePayrollDBService.getMaxSalaryByGender();
            } catch (DatabaseException e) {
                System.out.println(e.getMessage());
            }
        }
        return null;
    }

    /**
     * @param service
     * @return Map containing gender as key and employee count as value
     */
    public Map<String, Integer> readEmployeeCountByGender(IOService service) {
        if(service.equals(IOService.DB_IO)) {
            try {
                return employeePayrollDBService.getCountByGender();
            } catch (DatabaseException e) {
                System.out.println(e.getMessage());
            }
        }
        return null;
    }

    /**
     * @param name
     * @param salary
     * @throws DatabaseException
     * To Update Employee Salary
     */
    public void updateEmployeeSalary(String name, double salary) throws DatabaseException {
        int result = employeePayrollDBService.updateEmployeeSalaryUsingPreparedStatement(name, salary);
        if (result != 0) {
            EmployeePayrollData employeePayrollData = getEmployeeData(name);
            if(employeePayrollData != null) employeePayrollData.setSalary(salary);
        }
    }

    /**
     * @param name
     * @return T/F whether Payroll is in sync with DB or not
     * @throws DatabaseException
     */
    public boolean isEmployeePayrollInSyncWithDB(String name) throws DatabaseException {
        ArrayList<EmployeePayrollData> list = employeePayrollDBService.getEmployeeData(name);
        return list.get(0).equals(getEmployeeData(name));
    }

    /**
     * @param name
     * @returns Employee Payroll Data Object with given employee name
     */
    private EmployeePayrollData getEmployeeData(String name) {
        return employeePayrollDataList.stream()
                .filter(employeePayrollData -> employeePayrollData.getEmpName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public long employeeDataSize() {
        return employeePayrollDataList.size();
    }
}