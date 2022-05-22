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
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ApplicationExtension.class)
class AdminControllerTest {

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
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("admin.fxml"));
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.setTitle("Admin Test");
        primaryStage.show();
    }

    @Test
    @DisplayName("Verify that logout button is working properly")
    void testAdminLogoutButton(FxRobot robot) {
        robot.clickOn("#adminLogoutButton");
        robot.clickOn("#loginWindow");
    }

    @Test
    @DisplayName("Verify that remove destination button is working properly")
    void testAdminRemoveDestinationButton(FxRobot robot) {
        robot.clickOn("#removeDestinationButton");
        robot.clickOn("#removeDestinationWindow");
    }

    @Test
    @DisplayName("Verify that add destination button is working properly")
    void testAdminAddDestinationButton(FxRobot robot) {
        robot.clickOn("#addDestinationButton");
        robot.clickOn("#addDestinationWindow");
    }
}