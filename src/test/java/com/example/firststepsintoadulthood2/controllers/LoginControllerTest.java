package com.example.firststepsintoadulthood2.controllers;

import com.example.firststepsintoadulthood2.services.FileSystemService;
import com.example.firststepsintoadulthood2.services.UserService;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class LoginControllerTest {

    public static final String TEST_USER = "testuser";
    public static final String NONEXISTENT_USER = "nonexistentuser";
    public static final String TEST_PASSWORD = "testpassword";
    public static final String TEST_INCORRECT_PASSWORD = "incorrectpassword";
    private LoginController controller = new LoginController();

    @Before
    public void setUp() throws Exception {
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomePath().toFile());
        UserService.loadUsersFromFile();

        controller.usernameField = new TextField();
        controller.passwordField = new PasswordField();
        controller.loginMessage = new Label();

        controller.passwordField.setText(TEST_PASSWORD);
        controller.usernameField.setText(TEST_USER);
    }

    @Test
    public void testNonexistentUser() throws IOException {
        UserService.loadUsersFromFile();
        try {
            controller.LoginButtonClick(new ActionEvent());
            controller.usernameField.setText(NONEXISTENT_USER);
            controller.passwordField.setText(TEST_INCORRECT_PASSWORD);
            assertEquals("User doesn't exist!", controller.loginMessage.getText());
        }catch (NullPointerException ignored){

        }
    }

    @Test
    public void testIncorrectPassword() throws IOException {
        UserService.loadUsersFromFile();
        try {
            controller.LoginButtonClick(new ActionEvent());
            controller.usernameField.setText(TEST_USER);
            controller.passwordField.setText(TEST_INCORRECT_PASSWORD);
            assertEquals("Incorrect password!", controller.loginMessage.getText());
        }catch (NullPointerException ignored){

        }
    }


    @Test
    public void testExistingUser() throws IOException {
        UserService.loadUsersFromFile();
        try {
            controller.LoginButtonClick(new ActionEvent());
            controller.usernameField.setText(TEST_USER);
            controller.passwordField.setText(TEST_PASSWORD);
            assertEquals("", controller.loginMessage.getText());
        }catch (NullPointerException ignored){

        }
    }

    @Test
    public void testNullFields() {
        try {
            controller.LoginButtonClick(new ActionEvent());
            assertNull(controller.usernameField);
            assertNull(controller.passwordField);
            assertEquals("User doesn't exist!", controller.loginMessage.getText());
        }catch (NullPointerException | IOException ignored){

        }
    }

}
