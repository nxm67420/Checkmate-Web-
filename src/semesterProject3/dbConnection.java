package semesterProject3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;

public class dbConnection {

    String dbName = "semesterProject";
    String user = "root";
    String password = "iCode0325!";
    protected static Connection conn;
    String stmt;
    PreparedStatement statement;
    ResultSet resultSet;

    public static Connection Connect() {
        try {
            System.out.println("In initializeJdbc");
            //Load the JDBC driver
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded");

            //Establish Connection
            conn = DriverManager.getConnection("jdbc:mysql://localhost/semesterProject", "root", "iCode0325");
            System.out.println("Database connected");

            //Statements
           /* String stmt = "SELECT * FROM menu_item WHERE id in (?,?,?,?)";*/

            // Create a Statement
            /*PreparedStatement statement = conn.prepareStatement(s //Server Connection
        try {
            //Server Connection
            new Thread(() -> {
                try {
                    // Create a server socket Step #1
                    ServerSocket serverSocket = new ServerSocket(8000);
                    System.out.println("Waiting For Connection...");

                    //Socket Connection Step #2
                    Socket socket2 = new Socket("localhost", 8000);

                    //txtOutput.appendText("Server started at " + new Date() + '\n');
                    System.out.println("Server Started @ " + new Date() + '\n');

                    // Listen for a connection request Step #3
                    Socket socket = serverSocket.accept();

                    //Socket Connection
                    System.out.println("Network Connection Established...");

                    // Create data input and output streams
                    DataInputStream inputFromClient = new DataInputStream(socket.getInputStream());
                    DataOutputStream outputToClient = new DataOutputStream(socket.getOutputStream());

                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (NullPointerException e) {
                    System.out.println("");
                }
            }).start();
        }
        catch(NullPointerException e){
            System.out.println(" ");
        }tmt);*/

            //Columns & Rows
            /*statement.setString(1, "1");
            statement.setString(2,"2");
            statement.setString(3,"3");
            statement.setString(4,"4");*/

            //Execution
            /*ResultSet resultSet = statement.executeQuery();*/

        }
        catch (Exception e){
            System.out.print("e");
        }
        return conn;
    }
    public static void main(String[] args){
        System.out.println(Connect() + " is what was returned");
    }
}
