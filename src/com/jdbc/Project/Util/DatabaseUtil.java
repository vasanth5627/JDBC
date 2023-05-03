package com.jdbc.Project.Util;
import java.sql.*;

public class DatabaseUtil {
    private static final String driverPath = "com.mysql.cj.jdbc.Driver";
    private static  final String dbUrl = "jdbc:mysql://localhost:3306/Jdbc_prac";
    private static final String username = "root";
    private static final String password="Nani5627@";

    //whenever we create an object of this class, driver will be loaded.
    public DatabaseUtil() throws ClassNotFoundException {
        Class.forName(driverPath);
    }//end of constructor

    public Connection getConnection() throws SQLException {
       return  DriverManager.getConnection(dbUrl,username,password);
    } //end of connection


}
