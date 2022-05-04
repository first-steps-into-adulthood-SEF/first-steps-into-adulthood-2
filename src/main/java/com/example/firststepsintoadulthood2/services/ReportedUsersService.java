package com.example.firststepsintoadulthood2.services;

import com.example.firststepsintoadulthood2.exceptions.CouldNotWritePostsException;
import com.example.firststepsintoadulthood2.model.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Stack;

import static com.example.firststepsintoadulthood2.services.FileSystemService.getPathToFile;

public class ReportedUsersService {

    private static Stack<User> reportedUsers;
    private static final Path REPORTED_USERS_PATH = getPathToFile("reported-users.json");


    public static void loadReportedUsersFromFile() throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        if (!Files.exists(REPORTED_USERS_PATH)) {

            FileUtils.copyURLToFile(Objects.requireNonNull(UserService.class.getClassLoader().getResource("proto-reports.json")), REPORTED_USERS_PATH.toFile());

        }


        reportedUsers = objectMapper.readValue(REPORTED_USERS_PATH.toFile(), new TypeReference<Stack<User>>() {});

    }


    public static void addReportedUsers(String username, String option, String description) throws CouldNotWritePostsException {

        reportedUsers.push(new User(username, option, description));
        persistReportedPosts();

    }



    private static void persistReportedPosts() throws CouldNotWritePostsException {
        try {

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(REPORTED_USERS_PATH.toFile(), reportedUsers);

        } catch (IOException e) {

            throw new CouldNotWritePostsException();

        }
    }
}
