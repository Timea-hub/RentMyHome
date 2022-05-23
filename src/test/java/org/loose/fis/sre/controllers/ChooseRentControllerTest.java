package org.loose.fis.sre.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.loose.fis.sre.exceptions.RentIncompleteException;
import org.loose.fis.sre.model.Renting;
import org.loose.fis.sre.services.RentingService;
import org.loose.fis.sre.services.FileSystemService;
import org.loose.fis.sre.services.UserService;
import org.testfx.api.FxRobot;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

@ExtendWith(ApplicationExtension.class)
class ChooseRentControllerTest {

    public static final String CITY = "Sibiu";
    public static final String ANOTHER_CITY = "another city";
    public static final String RENT = "rent";
    public static final int CAPACITY = 100;
    public static final String CLEANINGSERVICE = "cleaningService";
    public static final double CLEANINGSERVICEPRICE = 100;
    public static final double PRICE = 100;

    @BeforeEach
    void setUp() throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".test-registration-example";
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        UserService.initDatabase();
        RentingService.initDatabase();
    }

    @AfterEach
    void tearDown() throws Exception {
        RentingService.close();
        UserService.close();
    }

    @Start
    void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("chooseRent.fxml"));
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.setTitle("Client Test");
        primaryStage.show();
    }

    @Test
    @DisplayName("Verify logout button is working properly")
    void testBackButton(FxRobot robot) {
        robot.clickOn("#cancelButton");
        robot.clickOn("#clientWindow");
    }

    @Test
    @DisplayName("Test - booking is working properly")
    void testBookingWorksProperly(FxRobot robot) throws Exception {

        //RentingService.addRent(CITY,RENT,CAPACITY,CLEANINGSERVICE,CLEANINGSERVICEPRICE,PRICE);

        robot.clickOn("#refreshTableButton");
        robot.clickOn("#tableView");
        robot.clickOn("Sibiu");
        robot.clickOn("#noOfNightsTextField");
        robot.write("3");

        Assertions.assertThat(robot.lookup("#totalPriceText").queryText()).hasText(String.format("450.0"));
    }

    @Test
    @DisplayName("Test - search field is working properly")
    void testSearchFieldIsWorkingProperly(FxRobot robot) throws Exception {

        //RentingService.addRent(CITY,RENT,CAPACITY,CLEANINGSERVICE,CLEANINGSERVICEPRICE,PRICE);
        //RentingService.addRent(ANOTHER_CITY,RENT,CAPACITY,CLEANINGSERVICE,CLEANINGSERVICEPRICE,PRICE);

        robot.clickOn("#refreshTableButton");
        robot.clickOn("#searchByCityField");
        robot.write("Sibiu");
        robot.clickOn("#searchButton");
        robot.clickOn("#tableView");
        robot.clickOn("Sibiu");
        robot.clickOn("#noOfNightsTextField");
        robot.write("3");

        Assertions.assertThat(robot.lookup("#totalPriceText").queryText()).hasText(String.format("450.0"));
    }

}