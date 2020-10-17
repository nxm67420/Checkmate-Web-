package semesterProject3;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.sql.*;

public class RegisterController {
    @FXML
    private AnchorPane rootpane;
    @FXML
    TextField txtUsername, txtFirstName,txtLastName, txtEmail;
    @FXML
    PasswordField txtPassword;
    @FXML
    Label lblMessage;
    @FXML
    Hyperlink linkSignUp;
    @FXML
    Button btnLogin;

    //Variables
    private String username, firstName, lastName, password, email;

    public void register(){
        String username = txtUsername.getText();
        String firstName = txtFirstName.getText();
        String lastName = txtLastName.getText();
        String password = txtPassword.getText();
        String email = txtEmail.getText();

        if (txtUsername.getText().trim().isEmpty() || txtFirstName.getText().trim().isEmpty()
                || txtLastName.getText().trim().isEmpty() || txtPassword.getText().trim().isEmpty()
                || txtEmail.getText().trim().isEmpty()) {
            lblMessage.setVisible(true);
            lblMessage.setTextFill(Color.web("#ad171c", 1));
            lblMessage.setText("Fill In All TextFields");
            return;
        }

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/semesterProject", "root", "");
            //Statements
            String sql = "INSERT INTO user(username, first_name, last_name, password, email)" +
                            "VALUES(?,?,?,?,?)";

            // Create a Statement
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, firstName);
            preparedStatement.setString(3, lastName);
            preparedStatement.setString(4, password);
            preparedStatement.setString(5, email);

            //Execution
            int resultSet = preparedStatement.executeUpdate();
            //resultSet = preparedStatement.executeUpdate();

            System.out.println("Username: " + username);
            System.out.println("First Name: " + firstName);
            System.out.println("Last Name: " + lastName);
            System.out.println("Email: " + email);
        }
        catch(Exception e){
            System.out.println(e);
        }

        txtUsername.clear();
        txtFirstName.clear();
        txtLastName.clear();
        txtPassword.clear();
        txtEmail.clear();
        lblMessage.setVisible(false);
    }

    //Get UserName
    public String getUserName() {
        return username;
    }

    //Set User Name
    public void setUserName() {
       this.username = txtUsername.getText();
    }

    //Get First Name
    public String getFirstName() {
        return firstName;
    }

    //Set First Name
    public void setFirstName() {
        this.firstName = txtFirstName.getText();
    }

    //Get Last Name
    public String getLastName() {
        return lastName;
    }

    //Set Last Name
    public void setLastName() {
        this.lastName = txtLastName.getText();
    }

    //Get Password
    public String getPassword() {
        return password;
    }

    //Set Password
    public void setPassword() {
        this.password = txtPassword.getText();
    }

    //Get Email
    public String getEmail() {
        return email;
    }

    //Set Email
    public void setEmail() {
        this.email = txtEmail.getText();
    }

    //Method 2
    //Exit Register Page
    public void exitRegister() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Login.fxml"));
        rootpane.getChildren().setAll(pane);
    }

    //Method 3
    //Goes To RegisterPage
    public void goRegister() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Register.fxml"));
        rootpane.getChildren().setAll(pane);
        pane.requestFocus();
    }

    //Method #3
    //Goes To Home Screen
    public void signIn() throws IOException {
        System.out.println(txtUsername.getText());
        System.out.println(getUserName());
        System.out.println(getPassword());
        //Goes to Main Menu Page
        //AnchorPane pane = FXMLLoader.load(getClass().getResource("Home.fxml"));
        //rootpane.getChildren().setAll(pane);
    }
}
