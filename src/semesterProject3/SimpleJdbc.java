package semesterProject3;

import java.sql.*;
//FindGrade.java


public class SimpleJdbc {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        // Load the JDBC driver
        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("Driver loaded");


        // Connect to a database
        Connection connection = DriverManager.getConnection
                ("jdbc:mysql://localhost/javabook" , "root", "iCode0325");
        System.out.println("Database connected");

        // Create a statement
        Statement statement = connection.createStatement();

        // Execute a statement
        ResultSet resultSet = statement.executeQuery
                ("select firstName, mi, lastName " +
                        "from Student " +
                        "where lastName " + " = 'Smith'");

       // ResultSet resultSet2 = statement.executeQuery("select * from College");

        // Iterate through the result and print the student names
        while (resultSet.next())
            System.out.println(resultSet.getString(1) + "\t" +
                    resultSet.getString(2) + "\t" + resultSet.getString(3));

        // Close the connection
        connection.close();
    }
}