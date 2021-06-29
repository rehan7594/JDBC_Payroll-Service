package com.bridgelabz;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;

import com.bridgelabz.DatabaseException.ExceptionType;

public class EmployeePayrollDBService {

    public ArrayList<EmployeePayrollData> readEmployeeData() throws DatabaseException {
        ArrayList<EmployeePayrollData> list = new ArrayList<EmployeePayrollData>();
        String query = "SELECT * FROM employee_payrolljdbc";
        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                System.out.println(name);
                Double salary = resultSet.getDouble("salary");
                LocalDate date = resultSet.getDate("start").toLocalDate();
                list.add(new EmployeePayrollData(id, name, salary, date));
            }
            return list;
        } catch (SQLException e) {
            throw new DatabaseException("Error while executing the query", ExceptionType.UNABLE_TO_EXECUTE_QUERY);
        }
    }

    /**
     * @returns Connection Object
     * @throws DatabaseException
     *
     */

    private Connection getConnection() throws DatabaseException {
        String jdbcURL = "jdbc:mysql://localhost:3306/payroll_servicejdbc";
        String user = "root";
        String password = "Sql@777fa";
        Connection connection;
        try {
            connection = DriverManager.getConnection(jdbcURL, user, password);
            System.out.println("Connection successfully established!" + connection);
        } catch (SQLException e) {
            throw new DatabaseException("Unable to connect to the database", ExceptionType.UNABLE_TO_CONNECT);
        }
        return connection;
    }

    /**
     * @param name
     * @param salary
     * @return execute update output
     * @throws DatabaseException
     */
    public int updateEmployeeSalary(String name, double salary) throws DatabaseException {
        String query = String.format("UPDATE employee_payrolljdbc set salary = %.2f where name = '%s'", salary, name);
        try (Connection connection = getConnection();) {
            Statement statement = connection.createStatement();
            return statement.executeUpdate(query);
        } catch (SQLException e) {
            throw new DatabaseException("Error while executing the query", ExceptionType.UNABLE_TO_EXECUTE_QUERY);
        }
    }

    public int updateEmployeeSalaryUsingPreparedStatement(String name, double salary) throws DatabaseException {
        String query = String.format("UPDATE employee_payrolljdbc set salary = ? where name = ?");
        try (Connection connection = getConnection();) {
            PreparedStatement prepareStatement = connection.prepareStatement(query);
            prepareStatement.setString(2, name);
            prepareStatement.setDouble(1, salary);
            return prepareStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Error while executing the query", ExceptionType.UNABLE_TO_EXECUTE_QUERY);
        }
    }

    /**
     * @param name
     * @return list of employee data with given name
     * @throws DatabaseException
     */
    public ArrayList<EmployeePayrollData> getEmployeeData(String name) throws DatabaseException {
        return readEmployeeData().stream()
                .filter(employeePayrollData -> employeePayrollData.getEmpName().equals(name))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}