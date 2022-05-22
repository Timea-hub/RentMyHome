package org.loose.fis.sre.services;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.loose.fis.sre.exceptions.IncorrectPasswordException;
import org.loose.fis.sre.exceptions.UsernameAlreadyExistsException;
import org.loose.fis.sre.model.User;

import static org.testfx.assertions.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    public static final String ADMIN = "admin";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String WRONG_PASSWORD = "wrong password";

    /*@BeforeAll
    static void beforeAll() {
        System.out.println("Before Class");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("After Class");
    }*/


    @BeforeEach
    void setUp() throws Exception{
        FileSystemService.APPLICATION_FOLDER = ".test-registration-example";
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        UserService.initDatabase();
        RentingService.initDatabase();
    }

    @AfterEach
    void tearDown() {
        RentingService.close();
        UserService.close();
    }

    @Test
    @DisplayName("Database is initialized and there are no users")
    void testDataBaseIsInitializedAndNoUserIsPersisted() {
        assertThat(UserService.getAllUsers()).isNotNull();
        assertThat(UserService.getAllUsers()).isEmpty();
    }

    @Test
    @DisplayName("User is successfully persisted to Database")
    void testUserIsAddedToDatabase() throws UsernameAlreadyExistsException {

        UserService.addUser(ADMIN, ADMIN, ADMIN);
        assertThat(UserService.getAllUsers()).isNotEmpty();
        assertThat(UserService.getAllUsers()).size().isEqualTo(1);
        User user = UserService.getAllUsers().get(0);
        assertThat(user.getUsername()).isEqualTo(ADMIN);
        assertThat(user.getPassword()).isEqualTo(UserService.encodePassword(ADMIN, ADMIN));
        assertThat(user.getRole()).isEqualTo(ADMIN);
    }

    @Test
    @DisplayName("User can not be added twice")
    void testUserCanNoteAddedTwice() {
        assertThrows(UsernameAlreadyExistsException.class, () -> {
            UserService.addUser(ADMIN, ADMIN, ADMIN);
            UserService.addUser(ADMIN, ADMIN, ADMIN);
        });
    }

    @Test
    @DisplayName("Role is returned correctly")
    void testCorrectRoleReturned() throws Exception {
        UserService.addUser(USERNAME, PASSWORD, ADMIN);
        assertThat(UserService.getRole(USERNAME, PASSWORD)).isEqualTo(ADMIN);
    }

    @Test
    @DisplayName("Incorrect password")
    void testIncorrectPassword() throws Exception {
        UserService.addUser(USERNAME, PASSWORD, ADMIN);
        assertThrows(IncorrectPasswordException.class, () -> {
            UserService.getRole(USERNAME, WRONG_PASSWORD);
        });
    }

}