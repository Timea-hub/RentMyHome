package org.loose.fis.sre.controllers;

        import javafx.collections.FXCollections;
        import javafx.collections.ObservableList;
        import javafx.fxml.FXML;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.control.Button;
        import javafx.scene.Scene;
        import javafx.scene.Parent;
        import javafx.scene.control.TableColumn;
        import javafx.scene.control.TableView;
        import javafx.scene.control.cell.PropertyValueFactory;
        import javafx.stage.Stage;
        import org.loose.fis.sre.model.Renting;
        import org.loose.fis.sre.services.RentingService;

        import java.io.IOException;
        import java.util.List;

public class RemoveRentController {

    public TableView<Renting> table;
    @FXML
    private Button backButton;

    public TableColumn<Renting, String> city;
    public TableColumn<Renting, String> rent;
    public TableColumn<Renting, Integer> capacity;
    public TableColumn<Renting, String> cleaningService;
    public TableColumn<Renting, Double> cleaningServicePrice;
    public TableColumn<Renting, Double> price;

    private void initTableColumns(TableView<Renting> tableview)
    {
        city = new TableColumn<>("City");
        city.setMinWidth(100);
        city.setCellValueFactory(new PropertyValueFactory<>("city"));

        rent = new TableColumn<>("Rent");
        rent.setMinWidth(100);
        rent.setCellValueFactory(new PropertyValueFactory<>("rent"));

        capacity = new TableColumn<>("Capacity");
        capacity.setMinWidth(100);
        capacity.setCellValueFactory(new PropertyValueFactory<>("capacity"));

        cleaningService = new TableColumn<>("Cleaning service");
        cleaningService.setMinWidth(100);
        cleaningService.setCellValueFactory(new PropertyValueFactory<>("cleaningService"));

        cleaningServicePrice = new TableColumn<>("Cleaning service");
        cleaningServicePrice.setMinWidth(100);
        cleaningServicePrice.setCellValueFactory(new PropertyValueFactory<>("cleaningServicePrice"));

        price = new TableColumn<>("Price per Person");
        price.setMinWidth(100);
        price.setCellValueFactory(new PropertyValueFactory<>("price"));

        table.getColumns().addAll(city,rent,capacity,cleaningService,cleaningServicePrice,price);
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

    public void handleBack() throws IOException {
        Stage stage = (Stage) backButton.getScene().getWindow();
        Stage primary = new Stage();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("admin.fxml"));
        Scene nextScene = new Scene(root, 600, 400);
        primary.setScene(nextScene);
        primary.show();
        stage.close();
    }

    public void handleRemoveSelectedButton() throws IOException {
        Renting renting = table.getSelectionModel().getSelectedItem();
        RentingService.removeRent(renting);
        handleRefresh();
    }

}

