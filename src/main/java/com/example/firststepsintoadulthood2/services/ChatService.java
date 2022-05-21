package com.example.firststepsintoadulthood2.services;
import com.example.firststepsintoadulthood2.controllers.LoginController;
import com.example.firststepsintoadulthood2.model.Messages;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.example.firststepsintoadulthood2.services.FileSystemService.getPathToFile;


public class ChatService {

    private static List<Messages> chat;
    private static List<String> messages = new ArrayList<>();
    private static final Path PATH = getPathToFile("chats.json");

    public static void loadMessagesFromFile() throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        if (!Files.exists(PATH)) {

            FileUtils.copyURLToFile(Objects.requireNonNull(UserService.class.getClassLoader().getResource("proto-chat.json")), PATH.toFile());

        }


        chat = objectMapper.readValue(PATH.toFile(), new TypeReference<List<Messages>>() {});

    }

    public static void addMessage(String source, String destination, String msg) throws IOException {

        if(msg.equals("IDWMHA")){

            chat.add(new Messages(source, destination, messages));

        }
        else{

            messages.add(msg);

        }


        persistMessages();

    }


    private static void persistMessages() throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(PATH.toFile(), chat);

    }

    public static List<String> getMessages(){

        return messages;

    }


}

