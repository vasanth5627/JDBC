package com.jdbc.Employee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class CRUD {

    static Connection conn;
    private static final String url = "jdbc:mysql://localhost:3306/Jdbc_prac";
    private static final String user="root";
    private static final String password="Nani5627@";

    public static Connection getConnection(String url, String user, String pass) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection(url,user,password);
        return conn;
    }

    public static String createEmployee(){
        return "create table if not exists employee (emp_id int primary key auto_increment, name varchar(100) not null, address varchar(150),salary double not null)";

    }


    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        try{
            if(getConnection(url,user,password)!=null){
                System.out.println(conn);
                System.out.println("Connection established Successfully");
            }
            else{
                System.out.println(conn);
                System.out.println("Error in establishing connection");
            }
        } catch (Exception e){
            System.out.println(e.toString());
        }

        Statement statement = conn.createStatement();

        statement.executeUpdate(createEmployee());

        EmployeeDAO dao = new EmployeeDAO();
        Scanner sc = new Scanner(System.in);
        Employee e = new Employee(sc.nextLine(),sc.nextLine(),sc.nextDouble());
        if(dao.insertEmployee(e)){
            System.out.println("Employee inserted successfully");
        }

        List<Employee> employeeList = dao.getAllEmployee();
        for(Employee emp : employeeList){
            System.out.println(emp.toString());
        }








    }
}
