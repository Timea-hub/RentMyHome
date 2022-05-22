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
import org.loose.fis.sre.services.RentingService;
import org.loose.fis.sre.services.FileSystemService;
import org.loose.fis.sre.services.UserService;
import org.testfx.api.FxRobot;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ApplicationExtension.class)
class RemoveRentControllerTest {

    public static final String CITY = "Cluj";
    public static final String RENT = "Garsoniera";
    public static final int CAPACITY = 100;
    public static final String CLEANINGSERVICE = "Both";
    public static final double CLEANINGSERVICEPRICE = 20;
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
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("removeDestination.fxml"));
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.setTitle("Admin Test");
        primaryStage.show();
    }

    @Test
    @DisplayName("Verify that the close button is working properly")
    void testBackButton(FxRobot robot) {
        robot.clickOn("#backButton");
        robot.clickOn("#adminWindow");
    }

    @Test
    @DisplayName("Verify that remove destination controller is working properly")
    void testRemoveDestination(FxRobot robot) throws Exception{

        RentingService.addRent(CITY, RENT, CAPACITY,CLEANINGSERVICE,CLEANINGSERVICEPRICE, PRICE);

        robot.clickOn("#refreshTableButton");
        robot.clickOn("#tableView");
        robot.clickOn("garsoniera");


        assertThat(UserService.getAllUsers()).size().isEqualTo(0);
    }
}