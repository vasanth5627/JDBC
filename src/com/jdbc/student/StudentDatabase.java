package com.jdbc.student;
import java.sql.*;
import java.util.Scanner;

public class StudentDatabase {

    private static Connection connection=null;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {
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
            System.out.println("3. Show all Records");
            System.out.println("4. Callable Statement: Select record again");
            System.out.println("5. Callable Statement: Select record by rollNum");
            System.out.println("6. Update Record");
            System.out.println("7. Delete Record");
            System.out.println("8. Understanding Transactions");
            System.out.println("9. Batch Processing");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice){
                case 1:
                    studentDatabase.insertRecord();
                    break;
                case 2:
                    studentDatabase.selectRecord();
                    break;
                case 3:
                    studentDatabase.showAllRecords();
                    break;
                case 4:
                    studentDatabase.selectAllRecord();
                    break;
                case 5:
                    studentDatabase.selectRecordByRollNum();
                    break;
                case 6:
                    studentDatabase.updateRecord();
                    break;
                case 7:
                    studentDatabase.deleteRecord();
                    break;
                case 8:
                    studentDatabase.transaction();
                    break;
                case 9:
                    studentDatabase.batchProcessing();
                    break;
                default:
                    break;
            }
        }
        catch (Exception e){
            System.out.println(e.toString());
            throw new RuntimeException("Something went wrong");
        }
        finally {
            connection.close();
            scanner.close();
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

    private void selectRecord() throws SQLException {
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

    private void showAllRecords() throws  SQLException{
        System.out.println("Showing all Records");
        String sql = "select * from student";
        Statement statement =  connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()){
            System.out.println("Roll Number: "+resultSet.getString(1));
            System.out.println("Name: "+resultSet.getString(2));
            System.out.println("Percentage: "+resultSet.getString(3));
            System.out.println("Place: "+resultSet.getString(4));
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");

        }
    }

    private void selectAllRecord() throws SQLException {
        System.out.println("Iam in  my method");
        CallableStatement cl = connection.prepareCall("{call GET_ALL()}");
        ResultSet resultSet = cl.executeQuery();
        while (resultSet.next()){
            System.out.println("Roll Number: "+resultSet.getString(1));
            System.out.println("Name: "+resultSet.getString(2));
            System.out.println("Percentage: "+resultSet.getString(3));
            System.out.println("Place: "+resultSet.getString(4));
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
        }
    }
    private void selectRecordByRollNum() throws SQLException {
        boolean record= false;
        CallableStatement cl = connection.prepareCall("{call GET_Data(?)}");
        System.out.println("Enter RollNumber");
        cl.setInt(1,scanner.nextInt());
        ResultSet resultSet = cl.executeQuery();
        while (resultSet.next()){
            record = true;
            System.out.println("Roll Number: "+resultSet.getString(1));
            System.out.println("Name: "+resultSet.getString(2));
            System.out.println("Percentage: "+resultSet.getString(3));
            System.out.println("Place: "+resultSet.getString(4));
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
        }
        if(!record){
            System.err.println("No record Found for given Input");
        }
    }

    private void updateRecord() throws SQLException{
        System.out.println("Enter Roll Number of the student you want to update");
        int roll = scanner.nextInt();
        String sql = "Select * from student where roll_num ="+roll;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        if(resultSet.next()){
            System.out.println("Roll Number: "+resultSet.getString(1));
            System.out.println("Name: "+resultSet.getString(2));
            System.out.println("Percentage: "+resultSet.getString(3));
            System.out.println("Place: "+resultSet.getString(4));
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");

            System.out.println("What do you want to Update?");
            System.out.println("1. Name");
            System.out.println("2. Percentage");
            System.out.println("3. Address");

            int choice = Integer.parseInt(scanner.next());
            String q = "Update student set ";
            switch (choice){
                case 1:
                    System.out.println("User want to update Name");
                    System.out.println("Please enter Name");
                    scanner.nextLine();
                    String name = scanner.nextLine();
                    q+="name = ? where roll_num = "+roll;
                    PreparedStatement statement1 = connection.prepareStatement(q);
                    statement1.setString(1,name);
                    int res = statement1.executeUpdate();
                    if(res>0) System.out.println("Updated Successfully");
                    else System.err.println("Update Failed");

                    break;
                case 2:
                    System.out.println("User want to update Percentage");
                    System.out.println("Please enter Percentage");
                    double percentage = scanner.nextDouble();
                    q+="percentage = ? where roll_num = "+roll;
                    statement1 = connection.prepareStatement(q);
                    statement1.setDouble(1,percentage);
                     res = statement1.executeUpdate();
                    if(res>0) System.out.println("Updated Successfully");
                    else System.out.println("Update Failed");
                    break;
                case 3:
                    System.out.println("User want to update Address");
                    System.out.println("Please enter Percentage");
                    scanner.nextLine();
                    String address = scanner.nextLine();
                    q+="address = ? where roll_num = "+roll;
                    statement1 = connection.prepareStatement(q);
                    statement1.setString(1,address);
                    res = statement1.executeUpdate();
                    if(res>0) System.out.println("Updated Successfully");
                    else System.out.println("Update Failed");
                    break;
                default:
                    System.err.println("Not a valid option");
                    break;
            }

        }
        else{
            System.err.println("Record Not Found");
        }

    }

    private void deleteRecord() throws SQLException {
        System.out.println("Enter Roll Number you want to delete");
        int roll = scanner.nextInt();
        String sql = "Delete from student where roll_num= "+roll;
        Statement statement = connection.createStatement();
        int res = statement.executeUpdate(sql);
        if(res>0){
            System.out.println("Record deleted successfully");
        }
        else{
            System.err.println("Record Deletion failed");
        }
    }
    private void transaction() throws SQLException {
        connection.setAutoCommit(false);

        String sql1 = "insert into student(name,percentage,address) values (?,?,?)";
        String sql2 = "insert into student(name,percentage,address) values (?,?,?)";

        PreparedStatement preparedStatement = connection.prepareStatement(sql1);
        preparedStatement.setString(1,"vikas");
        preparedStatement.setDouble(2,79);
        preparedStatement.setString(3,"Delhi");
        int row1 = preparedStatement.executeUpdate();
        preparedStatement =connection.prepareStatement(sql2);
        preparedStatement.setString(1,"vijay");
        preparedStatement.setDouble(2,79);
        preparedStatement.setString(3,"Bangalore");
        int row2 = preparedStatement.executeUpdate();
        if(row1>0 && row2>0){
            connection.commit();
            System.out.println("Transaction successful");
        }
        else{
            System.err.println("Transaction Failed");
            connection.rollback();
        }
    }

    private void batchProcessing() throws SQLException {
        connection.setAutoCommit(false);
        String sql1 = "insert into student(name,percentage,address) values ('Raj',68,'Mumbai')";
        String sql2 = "insert into student(name,percentage,address) values ('Sonali',68,'Mumbai')";
        String sql3 = "insert into student(name,percentage,address) values ('Harsh',68,'Nagpur')";

        Statement statement = connection.createStatement();
        statement.addBatch(sql1);
        statement.addBatch(sql2);
        statement.addBatch(sql3);
        int[] rows =  statement.executeBatch();
        boolean transaction = true;
        for(int i:rows){
            if(i<=0){transaction = false;}

        }
        if(transaction){
            connection.commit();
            System.out.println("Batch Processed Successfully");
        }
        else {
            connection.rollback();
            System.err.println("Batch process Failed");
        }


    }
}
