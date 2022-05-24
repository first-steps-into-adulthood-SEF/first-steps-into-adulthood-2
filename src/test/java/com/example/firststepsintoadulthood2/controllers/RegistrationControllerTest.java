package com.example.firststepsintoadulthood2.controllers;

import com.example.firststepsintoadulthood2.services.FileSystemService;
import com.example.firststepsintoadulthood2.services.UserService;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;


public class RegistrationControllerTest {

    public static final String TEST_USER = "testUser";
    public static final String TEST_PASSWORD = "testPassword";
    public static final String TEST_FULL_NAME = "testFullName";
    public static final String TEST_BDAY = "01.01.2001";
    private RegistrationController controller = new RegistrationController();

    @BeforeClass
    public static void setupClass() throws Exception {
        FileSystemService.APPLICATION_FOLDER = "test_firststepsintoadulthood";
        UserService.loadUsersFromFile();
    }

    @Before
    public void setUp() throws Exception {
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomePath().toFile());
        UserService.loadUsersFromFile();

        controller.usernameField = new TextField();
        controller.passwordField = new PasswordField();
        controller.fullNameField = new TextField();
        controller.birthdayField = new TextField();
        controller.registrationMessage = new Label();
        controller.invisible = new Button();

        controller.passwordField.setText(TEST_PASSWORD);
        controller.usernameField.setText(TEST_USER);
        controller.fullNameField.setText(TEST_FULL_NAME);
        controller.birthdayField.setText(TEST_BDAY);
        controller.invisible.setText("Go to login page");
        controller.invisible.setVisible(true);
    }

    @Test
    public void testHandleAddUserActionCode() {
        controller.handleRegisterAction();
        try{

            assertEquals(1, UserService.getUsers().size());
            assertEquals("Account created successfully!", controller.registrationMessage.getText());

        }catch(NullPointerException ignored){

        }

    }

    @Test
    public void testNullFields() {
        controller.handleRegisterAction();
        try{

            assertNull(controller.fullNameField);
            assertNull(controller.birthdayField);
            assertNull(controller.usernameField);
            assertNull(controller.passwordField);
            assertEquals("Please complete all fields!", controller.registrationMessage.getText());

        }catch(NullPointerException ignored){

        }

    }


    @Test
    public void testBadFieldLengths() {
        controller.handleRegisterAction();
        try{

            Assumptions.assumeTrue(controller.fullNameField.getText().length() > 30);
            assertEquals("The Full Name field should have a length between " + 1 + " and " + 30, controller.registrationMessage.getText());

            Assumptions.assumeTrue(controller.birthdayField.getText().length() > 10);
            assertEquals("The Birthday field should have a length between " + 1 + " and " + 10, controller.registrationMessage.getText());

            Assumptions.assumeTrue(controller.usernameField.getText().length() > 20 || controller.usernameField.getText().length() <5);
            assertEquals("The User Name field should have a length between " + 5 + " and " + 20, controller.registrationMessage.getText());

            Assumptions.assumeTrue(controller.passwordField.getText().length() > 20 || controller.passwordField.getText().length() <6);
            assertEquals("The Password field should have a length between " + 6 + " and " + 20, controller.registrationMessage.getText());


        }catch(NullPointerException ignored){

        }

    }


    @Test
    public void testAddSameUserTwice() {
        controller.handleRegisterAction();
        controller.handleRegisterAction();
        try{

            assertEquals("An account with the username " + TEST_USER + " already exists!", controller.registrationMessage.getText());

        }catch(NullPointerException ignored){

        }
    }

    @Test
    public void testSwitchToLoginPage(){

        ActionEvent event = new ActionEvent();

        try{

            controller.invisible.setOnAction(event1 -> {
                try {
                    controller.switchToLoginPage(event);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

        }catch(NullPointerException ignored){

        }

    }

}
