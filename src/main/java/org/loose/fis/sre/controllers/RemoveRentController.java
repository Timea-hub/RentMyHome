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
    public TableColumn<Renting, String> room;
    public TableColumn<Renting, String> typeOfTransport;
    public TableColumn<Renting, Double> pricePerson;

    private void initTableColumns(TableView<Renting> tableview)
    {
        city = new TableColumn<>("City");
        city.setMinWidth(100);
        city.setCellValueFactory(new PropertyValueFactory<>("city"));

        room = new TableColumn<>("Available house");
        room.setMinWidth(100);
        room.setCellValueFactory(new PropertyValueFactory<>("Available house"));

        typeOfTransport = new TableColumn<>("Cleaning service");
        typeOfTransport.setMinWidth(100);
        typeOfTransport.setCellValueFactory(new PropertyValueFactory<>("leaning service"));

        pricePerson = new TableColumn<>("Price per Person");
        pricePerson.setMinWidth(100);
        pricePerson.setCellValueFactory(new PropertyValueFactory<>("pricePerson"));

        table.getColumns().addAll(city,room,typeOfTransport,pricePerson);
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

