package com.jdbc.Project.Util;

import com.jdbc.Project.Model.Employee;

public class QueryUtil {
    public  static String insertEmployeeQuery(){
        return "insert into employee_info(name,address,salary) values(?,?,?)";
    }

    public  static String getAllEmployees(){
        return "select * from employee_info";
    }

    public static String getEmployeebyId(int id){
        return "select * from employee_info where emp_id = "+id;
    }

    public  static  String deleteEmployeebyId(int id){
        return "delete from employee_info where emp_id = "+id;
    }

    public static String updateEmployee(String name,String address, double salary, int id){
        String sql = "update employee_info set";

        if(name.length()>0 && !name.equalsIgnoreCase("NA")){
            sql+=" name = "+"'"+name+"'";
            if(salary>0 || address.length()>0 && !address.equalsIgnoreCase("NA") ) sql+=", ";
        }
        if(address.length()>0 && !address.equalsIgnoreCase("NA")){
            sql+=" address = "+"'"+address+"'";
            if(salary>0){
                sql+=", ";
            }
        }
        if(salary>0) sql+=" salary = "+"'"+salary+"'";
        sql+= " where emp_id = "+id;
        System.out.println(sql);
        return sql;

    }
}
