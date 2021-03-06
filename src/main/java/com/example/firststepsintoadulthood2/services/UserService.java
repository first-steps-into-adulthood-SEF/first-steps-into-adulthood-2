package com.example.firststepsintoadulthood2.services;

import com.example.firststepsintoadulthood2.exceptions.BadLengthFormatException;
import com.example.firststepsintoadulthood2.exceptions.NullFieldsException;
import com.example.firststepsintoadulthood2.model.User;
import com.example.firststepsintoadulthood2.exceptions.CouldNotWriteUsersException;
import com.example.firststepsintoadulthood2.exceptions.UserAlreadyExistsException;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.scene.image.Image;
import org.apache.commons.io.FileUtils;

import javax.crypto.BadPaddingException;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Objects;
import static com.example.firststepsintoadulthood2.services.FileSystemService.getPathToFile;


public class UserService {

    private static List<User> users;
    private static final Path USERS_PATH = getPathToFile("users.json");

    public static void loadUsersFromFile() throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        if (!Files.exists(USERS_PATH)) {

            FileUtils.copyURLToFile(Objects.requireNonNull(UserService.class.getClassLoader().getResource("prototype.json")), USERS_PATH.toFile());

        }


        users = objectMapper.readValue(USERS_PATH.toFile(), new TypeReference<List<User>>() {});

    }

    public static void addUser(String fullName, String birthday, String username, String password) throws UserAlreadyExistsException, CouldNotWriteUsersException, NullFieldsException, BadLengthFormatException {

        checkUserDoesNotAlreadyExist(username);
        checkNullFields(fullName, birthday, username, password);
        checkLength(fullName, birthday, username, password);
        users.add(new User(fullName, birthday, username, encodePassword(username, password)));
        persistUsers();

    }

    public static int checkCredentials(String username, String password){
        int flag = 0;
        for(User user : users){
            if(user.getUsername().equals(username)){
                flag=1;
                if(user.getPassword().equals(encodePassword(username, password))){
                    flag=2;
                }
            }
        }
        return flag;
    }

    private static void checkUserDoesNotAlreadyExist(String username) throws UserAlreadyExistsException {

        for (User user : users) {

            if (Objects.equals(username, user.getUsername()) || username.equals("mali.admin") || username.equals("vali.admin"))  {

                throw new UserAlreadyExistsException(username);

            }

        }

    }

    private static void checkNullFields(String fullName, String birthday, String username, String password) throws NullFieldsException{

        if(Objects.equals(fullName, "") || Objects.equals(birthday, "") || Objects.equals(username, "") || Objects.equals(password, "")){

                throw new NullFieldsException();

        }

    }


    private static void checkLength(String fullName, String birthday, String username, String password) throws BadLengthFormatException {

        if(fullName.length() > 30){

            throw new BadLengthFormatException("'Full Name'", 30, 1);

        }


        if(birthday.length() > 10){

            throw new BadLengthFormatException("'Birthday'", 10, 1);

        }


        if(username.length() > 20 || username.length() < 5){

            throw new BadLengthFormatException("'Username'", 20, 5);

        }

        if(password.length() > 20 || password.length() < 6){

            throw new BadLengthFormatException("'Password'", 20, 6);

        }


    }



    private static void persistUsers() throws CouldNotWriteUsersException {
        try {

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(USERS_PATH.toFile(), users);

        } catch (IOException e) {

                throw new CouldNotWriteUsersException();

        }
    }

    private static String encodePassword(String salt, String password) {

        MessageDigest md = getMessageDigest();
        md.update(salt.getBytes(StandardCharsets.UTF_8));

        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));

        return new String(hashedPassword, StandardCharsets.UTF_8)
                .replace("\"", ""); //to be able to save in JSON format
    }

    private static MessageDigest getMessageDigest() {

        MessageDigest md;

        try {

            md = MessageDigest.getInstance("SHA-512");

        } catch (NoSuchAlgorithmException e) {

            throw new IllegalStateException("SHA-512 does not exist!");

        }

        return md;

    }


    public static void changePfp(String usrnaname, String filePath) {

        for (User u : users) {

            if (u.getUsername().equals(usrnaname)) {

                u.setImagePath(filePath);

                try {

                    persistUsers();
                }
                catch (CouldNotWriteUsersException e) {

                    e.printStackTrace();

                }
                return;
            }
        }
    }


    public static void changeBio(String usernname, String bio) {

        for (User u : users) {

            if (u.getUsername().equals(usernname)) {

                u.setProfileDescription(bio);

                try {

                    persistUsers();

                } catch (CouldNotWriteUsersException e) {

                    e.printStackTrace();

                }
                return;
            }
        }
    }



    public static User getUser(String keepUsername) {

        for(User u : users){

            if(u.getUsername().equals(keepUsername))
                return u;

        }

        return null;
    }

    public static List<User> getUsers() {

        return users;

    }


}
