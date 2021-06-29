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

    /**
     * @param name
     * @param salary
     * @throws DatabaseException
     * To Update Employee Salary
     */
    public void updateEmployeeSalary(String name, double salary) throws DatabaseException {
        int result = new EmployeePayrollDBService().updateEmployeeSalary(name, salary);
        if(result == 0) return;
        EmployeePayrollData employeePayrollData = getEmployeeData(name);
        if(employeePayrollData != null) employeePayrollData.setSalary(salary);
    }

    /**
     * @param name
     * @return T/F whether Payroll is in sync with DB or not
     * @throws DatabaseException
     */
    public boolean isEmployeePayrollInSyncWithDB(String name) throws DatabaseException {
        ArrayList<EmployeePayrollData> list = new EmployeePayrollDBService().getEmployeeData(name);
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