package com.example.firststepsintoadulthood2.services;
import com.example.firststepsintoadulthood2.exceptions.BadLengthFormatException;
import com.example.firststepsintoadulthood2.exceptions.NullFieldsException;
import com.example.firststepsintoadulthood2.model.Messages;
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


public class ChatService {

    private static List<Messages> msg;
    private static final Path PATH = getPathToFile("chats.json");

    public static void loadUsersFromFile() throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        if (!Files.exists(PATH)) {

            FileUtils.copyURLToFile(Objects.requireNonNull(UserService.class.getClassLoader().getResource("proto-chat.json")), PATH.toFile());

        }


        msg = objectMapper.readValue(PATH.toFile(), new TypeReference<List<Messages>>() {});

    }

    public static void addMessage(String source, String destination, List<String> messages) throws IOException {

        msg.add(new Messages(source, destination, messages));
        persistMessages();

    }


    private static void persistMessages() throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(PATH.toFile(), msg);

    }


}

