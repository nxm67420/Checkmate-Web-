package semesterProject3;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
import java.time.LocalDate;
import java.util.Date;

public class Event {
    @FXML
    public AnchorPane rootPane;
    @FXML
    TextField txtTopic, txtBuilding, txtRoom, txtDate, txtTime;
    @FXML
    Label lblError,lblWelcome;
    @FXML
    TextArea txtOutput;

    String topic, building, room,date, timeOf;
    ResultSet resultSet=null;

    public void Event() {
        topic = txtTopic.getText();
        building = txtBuilding.getText();
        room = txtRoom.getText();
        date = txtDate.getText();
        timeOf = txtTime.getText();

        //Textfield Checking
        if (txtTopic.getText().trim().isEmpty() || txtBuilding.getText().trim().isEmpty()
                || txtRoom.getText().trim().isEmpty() || txtDate.getText().trim().isEmpty()|| txtTime.getText().trim().isEmpty()) {
            lblError.setVisible(true);
            lblError.setTextFill(Color.web("#ad171c", 1));
            lblError.setText("Fill In All TextFields");
            return;
        }

        //Inserts Info To Database
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/semesterProject", "root", "iCode0325");
            //Statements
            String sql = "INSERT INTO session(topic_name, building_name, room_num, session_date, time)" + "VALUES(?,?,?,?,?)";

            // Create a Statement
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setString(1, topic);
            preparedStatement.setString(2, building);
            preparedStatement.setString(3, room);
            preparedStatement.setString(4, date);
            preparedStatement.setString(5, timeOf);

            //Execution Returns Amount of Results
            int resultSet = preparedStatement.executeUpdate();

            //Server Connection
            /*connectToClient(topic, building, room, date, timeOf);
            connectToServer();*/


                if(resultSet > 0) {
                    System.out.println("Session Topic: " + topic);
                    System.out.println("Building Name: " + building);
                    System.out.println("Room Number: " + room);
                    System.out.println("Date: " + date);
                    System.out.println("Time: " + timeOf);
                }
                else{
                    System.out.println("No Results Found");
                    return;
                }
        }
        catch (SQLException e) {
            System.out.println(e);
        }
        catch (Exception e) {
            System.out.println(e);
        }

        //Clear TextFields
        txtTopic.clear();
        txtBuilding.clear();
        txtRoom.clear();
        txtDate.clear();
        txtTime.clear();
        lblError.setVisible(false);
    }

    //Populate TextArea Output
    public void populate() throws SQLException, IOException {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/semesterProject", "root", "iCode0325");

        //Statements
        String sql = "SELECT * FROM session WHERE id < ?";

        // Create a Statement
        PreparedStatement preparedStatement = conn.prepareStatement(sql);

        preparedStatement.setInt(1, 100);

        //Execution Returns Amount of Results
        resultSet = preparedStatement.executeQuery();

        //Fetch Results
        while(resultSet.next()) {
            txtOutput.appendText("ID :" + resultSet.getString(1));
            txtOutput.appendText("\nTopic :" + resultSet.getString(2));
            txtOutput.appendText("\nBuilding :" + resultSet.getString(3));
            txtOutput.appendText("\nRoom Number :" + resultSet.getString(4));
            txtOutput.appendText("\nDate :" + resultSet.getString(5));
            txtOutput.appendText("\nTime :" + resultSet.getString(6) + "\n\n");

        }
    }

    //Navigates To Created Sessions #TOP
    public void availableSessions() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("availableSessions.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    //Navigates To Create A Session #BOTTOM
    public void createSession() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Create_Session.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    //Navigates To Login Page
    public void signOut() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Login.fxml"));
        rootPane.getChildren().setAll(pane);
        lblWelcome.setVisible(false);
    }

    //Navigates To Home Page
    public void btnBack() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Home.fxml"));
        rootPane.getChildren().setAll(pane);
    }

/*    //Server Connections
    //Client <-- Server (*Server To Client Connection)
    public void connectToServer(){
        //Sends Output to Server and Server Prints To ScrollPane
        new Thread(() -> {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    // if you change the UI, do it here !
            try {
                //create socket to connect to the server
                Socket connectToServer = new Socket("localhost", 9000);
                System.out.println("Connected");

                //create an input stream to receive data from the server
                DataInputStream isFromServer = new DataInputStream(connectToServer.getInputStream());

                //create an output stream to send data to the server
                DataOutputStream osToServer = new DataOutputStream(connectToServer.getOutputStream());

                //send input to the server
                osToServer.writeUTF(topic);
                osToServer.writeUTF(building);
                osToServer.writeUTF(room);
                osToServer.writeUTF(date);
                osToServer.writeUTF(timeOf);

                osToServer.flush();

                //Get Employee from the Server
                String result = isFromServer.readUTF();


                availableSessions();

                //Prints Server Output
                txtOutput.appendText(result + "\n");

            }
            catch (IOException e) {
                e.printStackTrace();
            }
                }
            });
        }).start();
    }

    //Client --> Server
    public void connectToClient(String name, String location, String room, String dateSet, String timeOf) {
        new Thread(()-> {
            Platform.runLater(new Runnable() {
                private String timeOf;
                private String date;
                private String room;
                private String building;
                private String topic;
                @Override
                public void run() {
                    // if you change the UI, do it here !
            //set up the socket
            try {

                //create a server socket
                ServerSocket serverSocket = new ServerSocket(9000);

                //Platform.runLater(() -> text2.appendText("Server Started at " + new Date() + "\n"));

                Socket connectToClient = serverSocket.accept();

                //text2.appendText("CONNECTING...\n");

                //Display the client number
                //Platform.runLater(() -> text2.appendText("Connected to client  at " + new Date() + "\n"));

                //Create data input and output streams
                DataInputStream isFromClient = new DataInputStream(connectToClient.getInputStream());
                DataOutputStream osToClient = new DataOutputStream(connectToClient.getOutputStream());


                //Service the client
                while (true) {
                    //Receive the First Name from the client
                    this.topic = isFromClient.readUTF();

                    //Receive the Last Name from the client
                    this.building = isFromClient.readUTF();

                    //Receive the Salary from the client
                    this.room = isFromClient.readUTF();

                    //Receive the Salary from the client
                    this.date = isFromClient.readUTF();

                    //Receive the Salary from the client
                    this.timeOf = isFromClient.readUTF();

                    //Creates Employee Object
                    Session obj = new Session(topic, building, room, date, timeOf);

                    String results = obj.toString();

                    //send results back to the Client
                    osToClient.writeUTF(results);
                    //osToClient.close();
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
                }
            });
        }).start();
    }*/
}
