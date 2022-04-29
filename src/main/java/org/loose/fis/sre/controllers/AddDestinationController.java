package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;
import org.loose.fis.sre.exceptions.DestinationIncompleteException;


import java.io.IOException;
import java.util.Objects;

import static org.loose.fis.sre.services.UserService.*;

public class AddDestinationController {

    @FXML
    private ChoiceBox transportType;

    @FXML
    private Button saveButton;

    @FXML
    private Button cancelButton;

    @FXML
    private TextField countryField;

    @FXML
    private TextField cityField;

    @FXML
    private TextField hotelField;

    @FXML
    private TextField priceField;

    @FXML
    private Text addMessage;

    @FXML
    public void initialize() {
        transportType.getItems().addAll("Bus", "Plane");
    }


    public void handleSave() throws Exception {
        try {
            String pr = priceField.getText();
            if (!pr.matches("[0-9]+"))
            {
                addMessage.setText(("The price is invalid!"));
                return;
            }
            DestinationService.addDestination(cityField.getText(), hotelField.getText(), (String) transportType.getValue(), (Double.parseDouble(priceField.getText())));
            addMessage.setText("Destination added successfully!");
        } catch (DestinationIncompleteException e) {
            addMessage.setText(e.getMessage());
        }
    }


    public void handleCancelButton() throws IOException {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        Stage primary = new Stage();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("admin.fxml"));
        Scene nextScene = new Scene(root, 600, 400);
        primary.setScene(nextScene);
        primary.show();
        stage.close();
    }

}
