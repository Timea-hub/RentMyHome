package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;
import org.loose.fis.sre.exceptions.RentIncompleteException;
import org.loose.fis.sre.services.RentingService;


import java.io.IOException;

public class AddRentController {

    @FXML
    private ChoiceBox cleaningService;

    @FXML
    private Button saveButton;

    @FXML
    private Button cancelButton;

    @FXML
    private TextField cityField;

    @FXML
    private TextField rentField;

    @FXML
    private TextField capacity;

    @FXML
    private TextField priceField;

    @FXML
    private Text addMessage;

    public AddRentController() {
    }

    @FXML
    public void initialize() {
        cleaningService.getItems().addAll("Laundry", "Room cleaning", "Both", "None");
    }


    public void handleSave() throws Exception {
        try {
            String pr = priceField.getText();
            String cap = capacity.getText();
            if (!pr.matches("[0-9]+"))
            {
                addMessage.setText(("The price is invalid!"));
                return;
            }

            if (!cap.matches("[0-9]+"))
            {
                addMessage.setText(("The capacity is invalid!"));
                return;
            }
            RentingService.addRent(cityField.getText(), rentField.getText(),(Integer.parseInt(capacity.getText())), (String) cleaningService.getValue(), (Double.parseDouble(priceField.getText())), (Double.parseDouble(priceField.getText())) );
            addMessage.setText("Rent added successfully!");
        } catch(RentIncompleteException e) {
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
