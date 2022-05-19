package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;

public class ClientController {

    @FXML
    private Button chDestButton;

    @FXML
    private Button logoutButton;



    public void handleChooseRent() throws IOException {
        Stage primary = new Stage();
        Stage stage = (Stage) chDestButton.getScene().getWindow();
        stage.close();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("chooseRent.fxml"));
        Scene nextScene = new Scene(root, 600, 400);
        primary.setScene(nextScene);
        primary.show();
    }

    public void handleLogout() throws IOException {
        Stage primary = new Stage();
        Stage stage = (Stage) logoutButton.getScene().getWindow();
        stage.close();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
        Scene nextScene = new Scene(root, 600, 400);
        primary.setScene(nextScene);
        primary.show();
    }

}
