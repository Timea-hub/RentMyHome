package org.loose.fis.sre.services;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.loose.fis.sre.exceptions.RentIncompleteException;
import org.loose.fis.sre.exceptions.IncorrectPasswordException;
import org.loose.fis.sre.exceptions.UsernameAlreadyExistsException;
import org.loose.fis.sre.model.Renting;
import org.loose.fis.sre.model.User;

import javax.crypto.Cipher;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class RentServiceTest {

    public static final String CITY = "city";
    public static final String RENT = "rent";
    public static final int CAPACITY = Integer.parseInt("capacity");
    public static final String CLEANINGSERVICE = "cleaningService";
    public static final double CLEANINGSERVICEPRICE = Double.parseDouble("cleaningServicePrice");
    public static final double PRICE = 100;

    @BeforeEach
    void setUp() throws Exception{
        FileSystemService.APPLICATION_FOLDER = ".test-registration-example";
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        //UserService.initDatabase();
        RentingService.initDatabase();
    }

    @AfterEach
    void tearDown() {
        RentingService.close();
        //  UserService.close();
    }

    @Test
    @DisplayName("Destination database is initialized and there are no users")
    void testDataBaseIsInitializedAndNoUserIsPersisted() {
        assertThat(RentingService.getAllUsers()).isNotNull();
        //assertThat(DestinationService.getAllUsers()).isEmpty();
    }

    @Test
    @DisplayName("Destination is successfully persisted to Database")
    void testDestinationIsAddedToDatabase() throws RentIncompleteException {

        RentingService.addRent(CITY,RENT,CAPACITY,CLEANINGSERVICE,CLEANINGSERVICEPRICE,PRICE);
        assertThat(RentingService.getAllUsers()).isNotEmpty();
        assertThat(RentingService.getAllUsers()).size().isEqualTo(1);

        Renting renting = RentingService.getAllUsers().get(0);

        assertThat(renting.getCity()).isEqualTo(CITY);
        assertThat(renting.getRent()).isEqualTo(RENT);
        assertThat(renting.getCapacity()).isEqualTo(CAPACITY);
        assertThat(renting.getCleaningService()).isEqualTo(CLEANINGSERVICE);
        assertThat(renting.getCleaningServicePrice()).isEqualTo(CLEANINGSERVICEPRICE);
        assertThat(renting.getPrice()).isEqualTo(PRICE);
    }

}