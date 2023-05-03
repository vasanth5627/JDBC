package com.jdbc.Project.Service;

import com.jdbc.Project.Model.Employee;
import com.jdbc.Project.Util.DatabaseUtil;
import com.jdbc.Project.Util.QueryUtil;
import com.mysql.cj.callback.UsernameCallback;

import java.sql.*;
import java.util.Scanner;

public class DatabaseService {
    DatabaseUtil databaseUtil = new DatabaseUtil();
    public static final String ANSI_RED = "\033[0;31m";;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\033[0;32m";
    public static final String ANSI_BLUE = "\033[0;34m";
    public static final String YELLOW_BACKGROUND = "\033[43m";
    public static final String RED_BOLD = "\033[1;31m";
    public DatabaseService() throws ClassNotFoundException {
    }


    public void insertEmployee(Employee e) throws SQLException {
        try (Connection connection = databaseUtil.getConnection()) {//this will automatically close the resources
            String sql = QueryUtil.insertEmployeeQuery();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, e.getEmployeeName());
            preparedStatement.setString(2, e.getEmployeeAddress());
            preparedStatement.setDouble(3, e.getEmployeeSalary());
            int res = preparedStatement.executeUpdate();
            if (res > 0) {
                System.out.println(ANSI_GREEN+"Record inserted successfully"+ANSI_RESET);
            } else {
                System.out.println(ANSI_RED+"Record insertion failed"+ANSI_RESET);
            }

        }

    }//End of insertEmployee Method

    public void getALlEmployees() throws SQLException {
        try(Connection connection = databaseUtil.getConnection()) {
            String sql = QueryUtil.getAllEmployees();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                    printEmployeeDetails(new Employee(resultSet.getInt("emp_id"),resultSet.getString("name"),
                            resultSet.getString("address"),resultSet.getDouble("salary")));

            }
        }
    }

    public void getEmployeeByID(int id) throws SQLException {
        try(Connection connection = databaseUtil.getConnection()){
            String sql = QueryUtil.getEmployeebyId(id);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if(resultSet.next()){
                printEmployeeDetails(new Employee(resultSet.getInt("emp_id"),resultSet.getString("name"),
                        resultSet.getString("address"),resultSet.getDouble("salary")));
            }
            else{
                System.out.println(RED_BOLD+YELLOW_BACKGROUND+"Sorry!! Provided ID is not available in Database"+ANSI_RESET);
            }

        }
    }

    public void deleteEmployeebyID(int id) throws  SQLException {
        try(Connection connection = databaseUtil.getConnection()){
            String sql = QueryUtil.deleteEmployeebyId(id);
            Statement statement = connection.createStatement();
            int res = statement.executeUpdate(sql);
            if(res>0){
                System.out.println(ANSI_GREEN+"Record Deleted successfully"+ANSI_RESET);
            }
            else{
                System.out.println(ANSI_RED+"Record Deletion failed"+ANSI_RESET);
            }

        }
    }

        public  void updateEmployee(int id) throws SQLException {
            System.out.println("Please enter the value for the corresponding fields");
            System.out.println("Enter NA if you don't want to update the field");

            try(Connection connection  = databaseUtil.getConnection()){
                Scanner scanner = new Scanner(System.in);
                System.out.println("Name");
                String Name = scanner.nextLine();
                System.out.println("Address");
                String address = scanner.nextLine();
                System.out.println("Salary");
                String salary = scanner.nextLine();
                double salary1=0.0;
                if(salary.length()>0 && !salary.equalsIgnoreCase("NA")){
                  salary1 = Double.parseDouble(salary);
                }
                String sql = QueryUtil.updateEmployee(Name,address,salary1,id);
                Statement statement = connection.createStatement();
                int res = statement.executeUpdate(sql);
                if(res>0){
                    System.out.println(ANSI_GREEN+"Data Updated successfully"+ANSI_RESET);
                }
                else{
                    System.out.println(ANSI_RED+"Data Updation failed"+ANSI_RESET);
                }


            }


        }
    private void printEmployeeDetails(Employee e){
        System.out.println(ANSI_BLUE+"Employee ID: "+e.getEmployeeId());
        System.out.println("Employee Name: "+e.getEmployeeName());
        System.out.println("Employee Address: "+e.getEmployeeAddress());
        System.out.println("Employee Salary: "+e.getEmployeeSalary()+ANSI_RESET);
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++");
    }
}
