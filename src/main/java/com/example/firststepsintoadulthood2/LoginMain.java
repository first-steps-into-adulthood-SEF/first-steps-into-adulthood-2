package com.example.firststepsintoadulthood2;

import com.example.firststepsintoadulthood2.controllers.PostPageController;
import com.example.firststepsintoadulthood2.services.PostService;
import com.example.firststepsintoadulthood2.services.UserDescriptionsService;
import com.example.firststepsintoadulthood2.services.UserService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginMain extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        UserService.loadUsersFromFile();
        PostService.loadPostsFromFile();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 700);
        stage.setTitle("First Steps Into Adulthood");
        Image img = new Image("icon.png");
        stage.getIcons().add(img);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}