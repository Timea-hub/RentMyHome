package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;
import java.io.IOException;


public class AdminController {

    @FXML
    private Button addRentButton;

    @FXML
    private Button removeRentButton;

    @FXML
    private Button logoutAdminButton;

    public void handleAddRent() throws IOException {
        Stage primary = new Stage();
        Stage stage = (Stage) addRentButton.getScene().getWindow();
        stage.close();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("addRent.fxml"));
        Scene nextScene = new Scene(root, 600, 400);
        primary.setScene(nextScene);
        primary.show();
    }

    public void handleRemoveDestination() throws IOException {
        Stage primary = new Stage();
        Stage stage = (Stage) removeRentButton.getScene().getWindow();
        stage.close();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("removeRent.fxml"));
        Scene nextScene = new Scene(root, 600, 400);
        primary.setScene(nextScene);
        primary.show();
    }

    public void handleLogoutAdmin() throws IOException {
        Stage primary = new Stage();
        Stage stage = (Stage) logoutAdminButton.getScene().getWindow();
        stage.close();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
        Scene nextScene = new Scene(root, 600, 400);
        primary.setScene(nextScene);
        primary.show();
    }
}
