package org.loose.fis.sre.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;
import org.loose.fis.sre.model.Renting;
import org.loose.fis.sre.services.RentingService;
import java.io.IOException;
import java.util.List;

public class ChooseRentController {

    public TextField noOfNights;
    public TextField searchByCity;
    public Text totalPriceText;
    @FXML
    private Button cancelButton;

    public TableView<Renting> table;
    @FXML
    private Button backButton;

    public TableColumn<Renting, String> city;
    public TableColumn<Renting, String> rent;
    public TableColumn<Renting, String> cleaningService;
    public TableColumn<Renting, Double> pricePerson;

    private void initTableColumns(TableView<Renting> tableview)
    {
        city = new TableColumn<>("City");
        city.setMinWidth(100);
        city.setCellValueFactory(new PropertyValueFactory<>("city"));

        rent = new TableColumn<>("Rent");
        rent.setMinWidth(100);
        rent.setCellValueFactory(new PropertyValueFactory<>("rent"));

        cleaningService = new TableColumn<>("Cleaning service");
        cleaningService.setMinWidth(100);
        cleaningService.setCellValueFactory(new PropertyValueFactory<>("cleaningService"));

        pricePerson = new TableColumn<>("Price per Person");
        pricePerson.setMinWidth(100);
        pricePerson.setCellValueFactory(new PropertyValueFactory<>("pricePerson"));

        table.getColumns().addAll(city,rent,cleaningService,pricePerson);
    }

    public void handleCancel() throws IOException {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        Stage primary = new Stage();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("client.fxml"));
        Scene nextScene = new Scene(root, 600, 400);
        primary.setScene(nextScene);
        primary.show();
        stage.close();
    }

    public void handleRefresh()
    {
        table.getItems().clear();
        table.getColumns().clear();
        table.setEditable(true);

        initTableColumns(table);

        List<Renting> clientsList = RentingService.getAllRents();
        final ObservableList<Renting> data = FXCollections.observableArrayList(clientsList);
        table.setItems(data);
    }

    public void handleSearch(){
        String city = searchByCity.getText();

        List<Renting> clientsList = RentingService.getHousebyCity(city);
        final ObservableList<Renting> data = FXCollections.observableArrayList(clientsList);
        table.setItems(data);
    }

    double price;
    public void clickOnTableRow()
    {
        Renting renting = table.getSelectionModel().getSelectedItem();
        if(noOfNights.getText().equals(""))
        {
            totalPriceText.setText("Please choose number of nights");
            return;
        }
        price = Double.parseDouble(noOfNights.getText()) * renting.getPricePerson();
        totalPriceText.setText(Double.toString(price));
    }

    public void handleCalculate()
    {
        if (!noOfNights.getText().matches("[0-9]+"))
            return;
        clickOnTableRow();
    }

    public void handleConfirmation()
    {
        Alert confirm = new Alert(Alert.AlertType.INFORMATION);
        confirm.setTitle("Confirmation");
        confirm.setHeaderText("Yor booking was made!");
        confirm.setContentText("Total price is " + price);
        confirm.show();
    }
}