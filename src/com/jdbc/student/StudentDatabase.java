package com.jdbc.student;
import java.sql.*;
import java.util.Scanner;

public class StudentDatabase {

    private static Connection connection=null;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
            StudentDatabase studentDatabase = new StudentDatabase();
        //Load and register the driver
        try{
            String dbURL = "jdbc:mysql://localhost:3306/Jdbc_prac";
            String user = "root";
            String password="Nani5627@";
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(dbURL,user,password);
            System.out.println("Enter Choice");
            System.out.println("1. Insert Record");
            System.out.println("2. Select Record");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice){
                case 1:
                    studentDatabase.insertRecord();
                    break;
                case 2:
                    studentDatabase.selectRecord();
                    break;
                default:
                    break;
            }
        }
        catch (Exception e){
            throw new RuntimeException("Something went wrong");
        }
    }

    private void insertRecord() throws SQLException {
        System.out.println("Inside Insert Record");
        String sql  = "insert into student(name,percentage,address) values(?,?,?)";
        //create platform to perform sql query

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        System.out.println("Enter Name");
        preparedStatement.setString(1,scanner.nextLine());
        System.out.println("Enter Percentage");
        preparedStatement.setDouble(2,Double.parseDouble(scanner.nextLine()));
        System.out.println("Enter Address");
        preparedStatement.setString(3, scanner.nextLine());
        int rows = preparedStatement.executeUpdate();
        if(rows>0){
            System.out.println("Record Inserted Successfully");
        }
    }

    public void selectRecord() throws SQLException {
        System.out.println("Enter Roll_Number to find result");
        int roll = Integer.parseInt(scanner.nextLine());
        System.out.println("Inside select record");
        String sql = "select * from student where roll_num = "+roll;
        Statement statement =  connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        if(resultSet.next()){
                int roll_num = resultSet.getInt("roll_num");
                String name = resultSet.getString("name");
                double percentage = resultSet.getDouble("percentage");
                String address = resultSet.getString("address");
                System.out.println("Roll_num: "+roll_num);
                System.out.println("name: "+name);
                System.out.println("percentage: "+percentage);
                System.out.println("address: "+address);
        }
        else{
            System.out.println("No Records Found");
        }

    }
}
