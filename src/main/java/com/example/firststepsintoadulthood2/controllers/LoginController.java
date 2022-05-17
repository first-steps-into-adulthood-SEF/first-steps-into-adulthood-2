package com.example.firststepsintoadulthood2.controllers;

import com.example.firststepsintoadulthood2.Main;
import com.example.firststepsintoadulthood2.exceptions.CouldNotWritePostsException;
import com.example.firststepsintoadulthood2.model.Post;
import com.example.firststepsintoadulthood2.model.User;
import com.example.firststepsintoadulthood2.services.PostService;
import com.example.firststepsintoadulthood2.services.ReportedPostsService;
import com.example.firststepsintoadulthood2.services.UserService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.EventObject;
import java.util.List;
import java.util.Objects;
import java.util.Stack;


import static com.example.firststepsintoadulthood2.services.ReportedPostsService.loadReportedPostsFromFile;

public class LoginController {

    public TextField usernameField;
    public PasswordField passwordField;
    public Label loginMessage;
    protected static String keepUsername;

    public VBox verticalBox;
    int i = 0, j = 0;

    public static String postTitle;
    public static String postAuthor;
    public static String postDescription;
    public static String postDate;
    public static List<String> postComments;
    public static int profileCheck;


    @FXML
    public Label postTitleInPage;
    @FXML
    public Label postDetailsInPage;
    @FXML
    public Label descriptionInPage;
    @FXML
    public Label postAuthorInPage;
    @FXML
    public Button usernameInPage;
    @FXML
    public Text usernameInProfile;
    @FXML
    public Label bioInProfile;
    @FXML
    public Button editProfileButton;
    @FXML
    public ImageView profilePicture;
    @FXML
    public ImageView profilePicture2;

    private File filePath;


    public void switchToRegisterPage(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(Main.class.getResource("register.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void switchToForumPage(ActionEvent event) throws IOException{

        Parent root = FXMLLoader.load(Main.class.getResource("forum.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }



    @FXML
    protected void LoginButtonClick(ActionEvent event) throws IOException {

        if(UserService.checkCredentials(usernameField.getText(), passwordField.getText())==0){
            loginMessage.setText("User doesn't exist!");
        }
        else if(UserService.checkCredentials(usernameField.getText(), passwordField.getText())==1){
            loginMessage.setText("Incorrect password!");
        }
        else{
            keepUsername = usernameField.getText();
            switchToForumPage(event);
        }

    }

    @FXML
    public void initialize() {

        try {

            fillWithPosts();

            try{
                if(profileCheck == 2){

                    profileCheck = 0;
                    usernameInProfile.setText("@" + keepUsername);

                }
                else{
                    profileCheck = 0;

                    usernameInProfile.setText("@" + postAuthor);

                }

                bioInProfile.setText("");

                postTitleInPage.setText(postTitle);
                postDetailsInPage.setText("by " + postAuthor + ", " + postDate + "     ");
                descriptionInPage.setText(postDescription);


            }catch(NullPointerException e){

                //e.getMessage();

            }

            User user = UserService.getUser(keepUsername);

            if(user != null){

                String imgP = user.getImagePath();
                if(profilePicture != null){

                    profilePicture.setImage(new Image("file:///"+imgP));

                }

                if(profilePicture2 != null){

                    profilePicture2.setImage(new Image("file:///"+imgP));

                }



                String bio = user.getProfileDescription();
                if(bioInProfile != null){

                    bioInProfile.setText(bio);

                }

            }



        } catch (IOException e) {

            //e.printStackTrace();

        }

    }

    public void fillWithPosts() throws IOException {

        PostService.loadPostsFromFile();
        Stack<Post> posts = PostService.getPostList();

        for(Post post: posts){

            createPostBodyAndAddToHomePage(post);

        }
    }

    public void createPostBodyAndAddToHomePage(Post post) throws NullPointerException {

        Pane postPane = new Pane();

        postPane.prefHeight(300);
        postPane.prefWidth(600);

        Button title = new Button(post.getTitle() + "\n*by " + post.getUsername() + ", " + post.getDate() + "     ");
        title.setTextAlignment(TextAlignment.LEFT);
        title.setOnAction(event -> {
            try {
                postTitle=post.getTitle();
                postAuthor=post.getUsername();
                postDate=post.getDate();
                postDescription=post.getDescription();
                postComments=post.getReplies();

                Parent root = FXMLLoader.load(Main.class.getResource("postPage.fxml"));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                //e.printStackTrace();
            }

        });
        title.setCursor(Cursor.HAND);
        title.setStyle("-fx-font-size:15 Calibri");
        title.setLayoutX(-300);
        title.setPrefWidth(380);
        title.setPrefHeight(70);

        if(i != 0){

            title.setLayoutY(100 + i); i += 100;

        }

        title.setBackground(new Background(new BackgroundFill(Color.web("#C8A2C8"), CornerRadii.EMPTY, Insets.EMPTY)));

        Label space= new Label("");
        space.setLayoutY(75 + j);

        Image img = new Image("av30af0a6ffb584d0e709.png");
        ImageView view = new ImageView(img);
        view.setFitHeight(20); view.setFitWidth(20);
        view.setLayoutX(43); view.setLayoutY(300 + i);

        Button flag = new Button();
        flag.setGraphic(view);
        flag.setCursor(Cursor.HAND);
        flag.setPrefHeight(20); flag.setPrefWidth(20);
        flag.setBackground(new Background(new BackgroundFill(Color.web("#C8A2C8"), CornerRadii.EMPTY, Insets.EMPTY)));
        flag.setLayoutX(43);
        flag.setTooltip(new Tooltip("Report post"));
        flag.setOnAction(e->{
            try {
                selectReason(post.getTitle(), post.getDescription(), post.getUsername(), post.getDate());
            } catch (IOException ex) {
                //ex.printStackTrace();
            }
        });

        postPane.getChildren().add(title);
        postPane.getChildren().add(space);
        postPane.getChildren().add(flag);

        try{

            verticalBox.getChildren().add(postPane);

        }catch(NullPointerException e){

            //e.printStackTrace();

        }

        postPane.setLayoutY(1000 + i);

    }


    public void selectReason(String title, String description, String username, String date) throws IOException {

        loadReportedPostsFromFile();

        AnchorPane root = new AnchorPane();
        Button confirm = new Button("Confirm selection");
        Text text = new Text("Select from below one of the reasons for\n                 reporting this post :");

        confirm.setLayoutX(90);
        confirm.setLayoutY(120);
        confirm.setCursor(Cursor.HAND);
        confirm.setBackground(new Background(new BackgroundFill(Color.web("#C8A2C8"), CornerRadii.EMPTY, Insets.EMPTY)));

        text.setLayoutX(45);
        text.setLayoutY(50);


        String[] options = {"No useful contribution", "Offensive", "Not a suitable topic for the forum"};

        ChoiceBox cb = new ChoiceBox(FXCollections.observableArrayList(options));
        cb.setLayoutX(42);
        cb.setLayoutY(80);
        cb.setCursor(Cursor.HAND);
        cb.setBackground(new Background(new BackgroundFill(Color.web("#E6E6FA"), CornerRadii.EMPTY, Insets.EMPTY)));


        root.getChildren().add(confirm);
        root.getChildren().add(text);
        root.getChildren().add(cb);
        root.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

        Scene scene = new Scene(root, 300,200);

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.setTitle("Confirmation");
        stage.show();

        confirm.setOnMousePressed(e->{

            try {

                String selectedOption = (String)cb.getValue();

                ReportedPostsService.addReportedPosts(title, description, username, date, selectedOption);

            } catch (CouldNotWritePostsException ex) {

                ex.getMessage();

            }

            stage.close();
        });


    }



    public void returnToForum(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource("forum.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }



    public void displayOptions(ActionEvent actionEvent) {
        AnchorPane root = new AnchorPane();
        Button reportButton = new Button("Report user");
        Button viewProfileButton = new Button("View user profile");
        Button messageButton = new Button("Message user");
        reportButton.setLayoutX(110);
        reportButton.setLayoutY(30);
        reportButton.setCursor(Cursor.HAND);
        reportButton.setBackground(new Background(new BackgroundFill(Color.web("#C8A2C8"), CornerRadii.EMPTY, Insets.EMPTY)));
        viewProfileButton.setLayoutX(95);
        viewProfileButton.setLayoutY(90);
        viewProfileButton.setCursor(Cursor.HAND);
        viewProfileButton.setBackground(new Background(new BackgroundFill(Color.web("#C8A2C8"), CornerRadii.EMPTY, Insets.EMPTY)));
        messageButton.setLayoutX(105);
        messageButton.setLayoutY(150);
        messageButton.setCursor(Cursor.HAND);
        messageButton.setBackground(new Background(new BackgroundFill(Color.web("#C8A2C8"), CornerRadii.EMPTY, Insets.EMPTY)));
        root.getChildren().add(reportButton);
        root.getChildren().add(viewProfileButton);
        root.getChildren().add(messageButton);
        Scene scene = new Scene(root, 300,200);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.setTitle("Options");
        stage.show();

        reportButton.setOnAction(event -> {
            Parent root1 = null;
            try {
                root1 = FXMLLoader.load(Main.class.getResource("reportUser.fxml"));
            } catch (IOException e) {
                //e.printStackTrace();
            }
            Stage stage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene1 = new Scene(root1);
            stage1.setScene(scene1);
            stage1.show();
        });



        viewProfileButton.setOnAction(event -> {

            Parent root2 = null;
            try {
                root2 = FXMLLoader.load(Main.class.getResource("profile.fxml"));
            } catch (IOException e) {
                //e.printStackTrace();
            }

            Stage stage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene1 = new Scene(root2);
            stage1.setScene(scene1);
            stage1.show();

        });

        messageButton.setOnAction(event -> {

            Parent root3 = null;
            try {
                root3 = FXMLLoader.load(Main.class.getResource("chat.fxml"));
            } catch (IOException e) {
                //e.printStackTrace();
            }

            Stage stage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene1 = new Scene(root3);
            stage1.setScene(scene1);
            stage1.show();

        });

    }


    public void viewPersonalProfile(ActionEvent event) throws IOException {

        profileCheck = 2;

        Parent root = FXMLLoader.load(Main.class.getResource("personalProfile.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }



    public void handleEditProfile(){

        AnchorPane root = new AnchorPane();
        Button changeProfilePicture= new Button("Change profile picture");
        Button changeBio= new Button("Change bio");

        changeProfilePicture.setLayoutX(80);
        changeProfilePicture.setLayoutY(50);
        changeProfilePicture.setCursor(Cursor.HAND);
        changeProfilePicture.setBackground(new Background(new BackgroundFill(Color.web("#C8A2C8"), CornerRadii.EMPTY, Insets.EMPTY)));
        changeBio.setLayoutX(110);
        changeBio.setLayoutY(110);
        changeBio.setCursor(Cursor.HAND);
        changeBio.setBackground(new Background(new BackgroundFill(Color.web("#C8A2C8"), CornerRadii.EMPTY, Insets.EMPTY)));

        root.getChildren().add(changeProfilePicture);
        root.getChildren().add(changeBio);

        Scene scene = new Scene(root, 300,200);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.setTitle("Options");
        stage.show();




        changeProfilePicture.setOnAction(event -> {

            Stage stage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();

            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open image");

            this.filePath = fileChooser.showOpenDialog(stage1);

            try{

                Image img = new Image( "file:///" + filePath);

                profilePicture.setImage(img);
                UserService.changePfp(keepUsername, filePath.getPath());

            }catch(Exception e){

                //System.out.println(e.getMessage());

            }

        });


        changeBio.setOnAction(event -> {

            Parent root2 = null;
            try {
                root2 = FXMLLoader.load(Main.class.getResource("new-description.fxml"));
            } catch (IOException e) {
                //e.printStackTrace();
            }

            Stage stage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene1 = new Scene(root2);
            stage1.setScene(scene1);
            stage1.show();

            NewProfileDescriptionController n = new NewProfileDescriptionController();

            try{

                bioInProfile.setText(n.getProfileBio());

            }catch(NullPointerException ignored){


            }

        });

    }

}
