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
import org.loose.fis.sre.model.Renting;
import org.loose.fis.sre.services.RentingService;
import org.loose.fis.sre.services.FileSystemService;
import org.loose.fis.sre.services.UserService;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import static org.junit.jupiter.api.Assertions.*;
import static org.testfx.assertions.api.Assertions.assertThat;

@ExtendWith(ApplicationExtension.class)
class LoginControllerTest {

    private static final String CLIENT = "Client";
    private static final String ADMIN = "Admin";
    private static final String CORRECT_USERNAME = "correctUsername";
    private static final String CORRECT_PASSWORD = "correct_password";
    private static final String WRONG_USERNAME = "wrongUsername";
    private static final String WRONG_PASSWORD = "wrongPassword";

    @BeforeEach
    void setUp() throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".test-registration-example";
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        UserService.initDatabase();
    }

    @AfterEach
    void tearDown() {
        UserService.close();
        RentingService.close();
    }

    @Start
    void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.setTitle("Login Test");
        primaryStage.show();
    }

    @Test
    @DisplayName("Test - Client successfully login")
    void testClientSuccessfullyLogin(FxRobot robot) throws Exception {
        UserService.addUser(CORRECT_USERNAME, CORRECT_PASSWORD, CLIENT);

        robot.clickOn("#username");
        robot.write(CORRECT_USERNAME);
        robot.clickOn("#password");
        robot.write(CORRECT_PASSWORD);
        robot.clickOn("#loginButton");
    }

    @Test
    @DisplayName("Test - Admin successfully login")
    void testAdminSuccessfullyLogin(FxRobot robot) throws Exception {
        UserService.addUser(CORRECT_USERNAME, CORRECT_PASSWORD, ADMIN);

        robot.clickOn("#username");
        robot.write(CORRECT_USERNAME);
        robot.clickOn("#password");
        robot.write(CORRECT_PASSWORD);
        robot.clickOn("#loginButton");
    }

    @Test
    @DisplayName("Test - Login failed - Account does not exist")
    void testLoginFailedAccountDoesNotExist(FxRobot robot) throws Exception {
        UserService.addUser(CORRECT_USERNAME, CORRECT_PASSWORD, ADMIN);

        robot.clickOn("#username");
        robot.write(WRONG_USERNAME);
        robot.clickOn("#password");
        robot.write(CORRECT_PASSWORD);
        robot.clickOn("#loginButton");
        assertThat(robot.lookup("#loginMessage").queryText()).hasText(String.format("The account does not exist"));
    }

    @Test
    @DisplayName("Test - Login failed - Wrong password")
    void testLoginFailedWrongPassword(FxRobot robot) throws Exception {
        UserService.addUser(CORRECT_USERNAME, CORRECT_PASSWORD, ADMIN);

        robot.clickOn("#username");
        robot.write(CORRECT_USERNAME);
        robot.clickOn("#password");
        robot.write(WRONG_PASSWORD);
        robot.clickOn("#loginButton");
        assertThat(robot.lookup("#loginMessage").queryText()).hasText(String.format("The password is wrong"));
    }

}