package semesterProject3;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import sun.plugin.javascript.navig.Anchor;

import java.io.IOException;
import java.sql.*;


public class LoginController {
    @FXML
    private AnchorPane rootpane;
    @FXML
    TextField txtUser;
    @FXML
    PasswordField txtPass;
    @FXML
    Hyperlink linkSignUp;
    @FXML
    Button btnLogin;
    @FXML
    Label lblMessage, lblWelcome;

    public LoginController() throws SQLException {
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
        try {
            String name = txtUser.getText();
            String pass = txtPass.getText();

            Connection conn =  DriverManager.getConnection("jdbc:mysql://localhost/semesterProject", "root", "iCode0325");

            String sql = "SELECT * FROM user WHERE username = ? AND password = ?";

            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setString(1,name);
            preparedStatement.setString(2,pass);

            ResultSet resultSet = preparedStatement.executeQuery();

            //Login Confirmation
            if(!resultSet.next()){
                lblMessage.setVisible(true);
                lblMessage.setTextFill(Color.web("#ff0000", 0.8));
                lblMessage.setText("Incorrect username / email");
                txtUser.clear();
                txtPass.clear();
            }
            else{
                lblMessage.setVisible(false);
                //Goes to Main Menu Page
                AnchorPane pane = FXMLLoader.load(getClass().getResource("Home.fxml"));
                rootpane.getChildren().setAll(pane);
                lblWelcome.setVisible(true);
                lblWelcome.setText("Welcome Back, " + resultSet);
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

}
