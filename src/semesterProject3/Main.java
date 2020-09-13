package semesterProject3;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Date;
import java.sql.*;
//Notes
/*
 * The onMessage() method has to be implemented by
 * a sublclass.  If used in conjunction with JavaFX,
 * use Platform.runLater() to force this method to run
 * on the main thread.
 */

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        primaryStage.setTitle("CheckMate");
        primaryStage.setScene(new Scene(root, 1200, 800));
        primaryStage.show();
        startServer();
        startClient();
        Connect();
    }
    //Start Server
    public void startServer(){
        new Thread(()-> {
            try {
                System.out.println("Waiting For Clients...");
                ServerSocket serverSocket1 = new ServerSocket(9000);
                System.out.println("Server Started :" + new Date());
                //Socket socket = new ServerSocket().accept();
                serverSocket1.accept();
                System.out.println("Server Connection Granted");
            }
            catch (SocketException e){
                System.out.println(e);
            }
            catch (Exception e) {
                System.out.println(e);
            }
            System.out.println("Connected");
        }).start();
    }

    //Start Client
    static public void startClient(){
        new Thread(()-> {
            try {
                Socket socket = new Socket("localhost", 9000);
                System.out.println("Client Connected");
            }
            catch (Exception e) {
                System.out.println(e);
            }
        }).start();
    }

    //Database Variables
    String dbName = "semesterProject";
    String user = "root";
    String password = "iCode0325!";
    protected static Connection conn;
    String stmt;
    PreparedStatement statement;
    ResultSet resultSet;

    //Database Connectivity
    public static Connection Connect(){
        new Thread(()->{
            try {
                System.out.println("In initializeJdbc");
                //Load the JDBC driver
                Class.forName("com.mysql.jdbc.Driver");
                System.out.println("Driver loaded");

                //Establish Connection
                conn = DriverManager.getConnection("jdbc:mysql://localhost/semesterProject", "root", "iCode0325");
                System.out.println("Database connected");
            }
            catch (Exception e){
                System.out.print(e);
            }

        }).start();
        return conn;
    }

    //Launch Program
    public static void main(String[] args) {
        launch(args);
    }
}
