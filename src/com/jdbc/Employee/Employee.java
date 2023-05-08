package com.jdbc.Employee;

public class Employee {
   private int emp_id;
   private String name;
   private String address;
   private double salary;

   public Employee(int emp_id, String name, String address, double salary) {
      this.emp_id = emp_id;
      this.name = name;
      this.address = address;
      this.salary = salary;
   }

   public Employee(String name, String address, double salary) {
      this.name = name;
      this.address = address;
      this.salary = salary;
   }

   public Employee(String name, double salary) {
      this.name = name;
      this.salary = salary;
   }

   public Employee() {
   }

   public int getEmp_id() {
      return emp_id;
   }



   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getAddress() {
      return address;
   }

   public void setAddress(String address) {
      this.address = address;
   }

   public double getSalary() {
      return salary;
   }

   public void setSalary(double salary) {
      this.salary = salary;
   }

   @Override
   public String toString() {
      return "Employee{" +
              "emp_id=" + emp_id +
              ", name='" + name + '\'' +
              ", address='" + address + '\'' +
              ", salary=" + salary +
              '}';
   }
}
