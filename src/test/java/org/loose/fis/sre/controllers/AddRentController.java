package org.loose.fis.sre.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.loose.fis.sre.services.RentingService;
import org.loose.fis.sre.services.FileSystemService;
import org.loose.fis.sre.services.UserService;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.testfx.assertions.api.Assertions.assertThat;

@ExtendWith(ApplicationExtension.class)
class AddRentControllerTest {

    public static final String CITY = "Bucuresti";
    public static final String RENT = "Garsoniera";
    public static final String CAPACITY = "6";
    public static final String CLEANING_SERVICE = "Both";
    public static final String CLEANING_SERVICE_PRICE = "80";
    public static final String PRICE = "600";
    public static final String INVALID_PRICE = "100euro";
    public static final String PLANE = "Plane";

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
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("addRent.fxml"));
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.setTitle("Add Destination Test");
        primaryStage.show();
    }

    @Test
    @DisplayName("Verify that cancel button is working properly")
    void testCancelButton(FxRobot robot) {
        robot.clickOn("#cancelButton");
        robot.clickOn("#adminWindow");
    }

    @Test
    @DisplayName("Add a destination correctly - cleaning service - both")
    void testAddDestinationCorrectlyForBoth(FxRobot robot) {
        robot.clickOn("#city");
        robot.write(CITY);
        robot.clickOn("#rent");
        robot.write(RENT);
        robot.clickOn("#capacity");
        robot.write(CAPACITY);
        robot.clickOn("#cleaningService");
        robot.clickOn("Both");
        //robot.clickOn("Both");
        robot.clickOn("#cleaningServicePrice");
        robot.write(CLEANING_SERVICE_PRICE);
        robot.clickOn("#price");
        robot.write(PRICE);

        robot.clickOn("#saveButton");
        assertThat(robot.lookup("#addRentMessage").queryText()).hasText("Rent added successfully!");
    }

    @Test
    @DisplayName("Invalid price - cleaning service")
    void testInvalidPriceForBus(FxRobot robot) {
        robot.clickOn("#city");
        robot.write(CITY);
        robot.clickOn("#rent");
        robot.write(RENT);
        robot.clickOn("#capacity");
        robot.write(CAPACITY);
        robot.clickOn("#cleaningService");
        //robot.clickOn(CLEANING_SERVICE);
        robot.clickOn("Both");
        robot.clickOn("#cleaningServicePrice");
        robot.write(INVALID_PRICE);
        robot.clickOn("#price");
        robot.write(PRICE);

        robot.clickOn("#saveButton");
        assertThat(robot.lookup("#addRentMessage").queryText()).hasText("The price is invalid!");
    }

    @Test
    @DisplayName("Add a destination correctly - laundry")
    void testAddDestinationCorrectlyForPlane(FxRobot robot) {
        robot.clickOn("#city");
        robot.write(CITY);
        robot.clickOn("#rent");
        robot.write(RENT);
        robot.clickOn("#capacity");
        robot.write(CAPACITY);
        robot.clickOn("#cleaningService");
        robot.clickOn("Laundry");
        //robot.clickOn(CLEANING_SERVICE);
        robot.clickOn("#cleaningServicePrice");
        robot.write(CLEANING_SERVICE_PRICE);
        robot.clickOn("#price");
        robot.write(PRICE);

        robot.clickOn("#saveButton");
        assertThat(robot.lookup("#addRentMessage").queryText()).hasText("Rent added successfully!");
    }

    @Test
    @DisplayName("Invalid price - rent price")
    void testInvalidPriceForPlane(FxRobot robot) {
        robot.clickOn("#city");
        robot.write(CITY);
        robot.clickOn("#rent");
        robot.write(RENT);
        robot.clickOn("#capacity");
        robot.write(CAPACITY);
        robot.clickOn("#cleaningService");
        robot.clickOn("Both");
        robot.clickOn(CLEANING_SERVICE);
        robot.clickOn("#cleaningServicePrice");
        robot.write(CLEANING_SERVICE_PRICE);
        robot.clickOn("#price");
        robot.write(INVALID_PRICE);

        robot.clickOn("#saveButton");
        assertThat(robot.lookup("#addRentMessage").queryText()).hasText("The price is invalid!");
    }

    @Test
    @DisplayName("Not enough data of the destination - empty fields")
    void test(FxRobot robot) {
        robot.clickOn("#capacity");
        robot.write(CAPACITY);
        robot.clickOn("#cleaningService");
        robot.clickOn("Both");
        //robot.clickOn(CLEANING_SERVICE);
        robot.clickOn("#cleaningServicePrice");
        robot.write(CLEANING_SERVICE_PRICE);
        robot.clickOn("#price");
        robot.write(PRICE);

        robot.clickOn("#saveButton");
        assertThat(robot.lookup("#addRentMessage").queryText()).hasText("Not enough data of the destination!");
    }

}