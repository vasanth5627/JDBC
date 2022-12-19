import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Jdbc_prac","root","Nani5627@");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from people");

        while(resultSet.next()){
            System.out.println(resultSet.getString("first_name"));
        }
    }
}