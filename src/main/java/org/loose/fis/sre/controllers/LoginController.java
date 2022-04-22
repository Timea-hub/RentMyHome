package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;
import org.loose.fis.sre.model.User;

import java.io.IOException;
import java.util.Objects;

import static org.loose.fis.sre.services.UserService.*;

public class LoginController {

    public Button registerBtn;
    public Button loginBtn;
    @FXML
    private Text warningText;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;


    public void handleLoginAction() throws Exception {

        Stage primary = (Stage) warningText.getScene().getWindow();
        try {
            //String role = getRole(usernameField.getText(), passwordField.getText());
            //User.setUser(usernameField.getText());

            //if(role.equals("Admin")) {
                Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("admin.fxml"));
                Scene nextScene = new Scene(root, 600, 400);
                primary.setScene(nextScene);
                primary.show();
            //} else if (role.equals("Client")){
                Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("client.fxml"));
                Scene nextScene = new Scene(root, 600, 400);
                primary.setScene(nextScene);
                primary.show();
            }
        //} catch (UsernameDoesNotExistsException ex) {
            warningText.setText("The account does not exist");
        //} catch (IncorrectPasswordException e) {
            warningText.setText("The password is wrong");
        }
    }

    public void handleRegisterAction() throws IOException {
        Stage primary = new Stage();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("register.fxml"));
        Scene nextScene = new Scene(root, 600, 400);
        primary.setScene(nextScene);
        primary.show();
    }
}
