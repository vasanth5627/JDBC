package com.jdbc.Project.Main;
import com.jdbc.Project.Model.Employee;
import com.jdbc.Project.Service.DatabaseService;

import java.sql.*;
import java.util.Scanner;
public class Main {
    public static final String ANSI_RED = "\033[0;31m";;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\033[0;32m";
    private static Connection connection=null;



    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        DatabaseService databaseService = new DatabaseService();
        try(Scanner scanner = new Scanner(System.in);){
            boolean isRunning = true;
            while (isRunning){
                System.out.println("Enter Choice");
                System.out.println("1. Insert");
                System.out.println("2. Select ALl");
                System.out.println("3. Select Employee by an Id");
                System.out.println("4. Delete Employee");
                System.out.println("5. Update Employee");
                System.err.println(ANSI_RED+"6. EXIT"+ANSI_RESET);
                boolean validChoice = false;
                int choice=0;
                do{
                    try{
                        choice = scanner.nextInt();
                        validChoice=true;
                    }
                    catch (Exception e){
                        scanner.next();
                        System.out.println("Please Enter a valid Number ");
                    }
                }while (!validChoice);


                switch (choice){
                    case 1:
                        System.out.println("Please Enter the following details");
                        scanner.nextLine();
                        System.out.println("Enter name");
                        String name = scanner.nextLine();
                        System.out.println("Enter address");
                        String address = scanner.nextLine();
                        System.out.println("Enter salary");
                        double salary = scanner.nextDouble();
                        databaseService.insertEmployee(new Employee(name,address,salary));
                        break;
                    case 2:
                        System.out.println("Here is the list of Employees");
                        databaseService.getALlEmployees();
                        break;
                    case 3:
                        System.out.println("Enter id of employee for details");
                        databaseService.getEmployeeByID(scanner.nextInt());
                        break;
                    case 4:
                        System.out.println("Enter id of employee you wish to Delete");
                        databaseService.deleteEmployeebyID(scanner.nextInt());

                        break;
                    case  5:
                        System.out.println("enter id of employee you wish to update");
                        databaseService.updateEmployee(scanner.nextInt());
                        break;
                    case 6:
                        isRunning=false;
                        System.out.println("Thank You for using our Application");
                        break;
                    default:
                        System.out.println("Not a valid Option");
                        break;
                }

            }

            }



    }
}
