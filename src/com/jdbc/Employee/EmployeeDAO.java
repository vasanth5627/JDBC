package com.jdbc.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EmployeeDAO { //DAO - data access object

    public boolean insertEmployee(Employee employee) throws SQLException {
        String sql = "Insert into employee (name, address, salary) values (?,?,?)";
        try {
            PreparedStatement statement = CRUD.conn.prepareStatement(sql);
            statement.setString(1, employee.getName());
            statement.setString(2, employee.getAddress());
            statement.setDouble(3, employee.getSalary());

            return statement.executeUpdate() > 0;
        }catch (Exception e){
            System.out.println(Arrays.toString(e.getStackTrace()));
            return false;
        }
    }

    public boolean deleteEmployee(int id) throws SQLException {
        String sql = "delete from employee where emp_id = "+id;
        try {
            Statement statement = CRUD.conn.createStatement();
            return statement.executeUpdate(sql)>0;

        }
        catch (Exception e){
            System.out.println(e.toString());
            return false;
        }

    }

    public Employee getEmployee(int id){
        String sql = "select * from employee where emp_id = "+id;
        try {
            Statement statement = CRUD.conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                Employee employee = new Employee(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDouble(4));
                return employee;
            }
            return new Employee();
        }
        catch (Exception e){
            System.out.println(e.toString());
            return null;
        }
    }

    public List<Employee> getAllEmployee(){
        List<Employee> emp_list = new ArrayList<>();
        String sql = "select * from employee";
        try{
            Statement statement = CRUD.conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                emp_list.add(new Employee(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDouble(4)));
            }
            return emp_list;
        }
        catch (Exception e){
            System.out.println(e.toString());
            return null;
        }

    }

}
