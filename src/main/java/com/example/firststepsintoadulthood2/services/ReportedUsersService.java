package com.example.firststepsintoadulthood2.services;

import com.example.firststepsintoadulthood2.exceptions.CouldNotWriteUsersException;
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
    public static String rememberUser;


    public static void loadReportedUsersFromFile() throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        if (!Files.exists(REPORTED_USERS_PATH)) {

            FileUtils.copyURLToFile(Objects.requireNonNull(UserService.class.getClassLoader().getResource("proto-reports.json")), REPORTED_USERS_PATH.toFile());

        }


        reportedUsers = objectMapper.readValue(REPORTED_USERS_PATH.toFile(), new TypeReference<Stack<User>>() {});

    }


    public static void addReportedUsers(String username, String reportedUser, String option, String description) throws CouldNotWriteUsersException {

        reportedUsers.push(new User(1, reportedUser, username, option, description));
        persistReportedUsers();

    }



    private static void persistReportedUsers() throws CouldNotWriteUsersException {
        try {

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(REPORTED_USERS_PATH.toFile(), reportedUsers);

        } catch (IOException e) {

            throw new CouldNotWriteUsersException();

        }
    }

    public static void sendReportToAdmins(String reporter, String reportedUser, String reportMotive, String reportDescription) {
        String notification = reporter + " reported " + reportedUser + ". Motive:" + reportMotive + ". Description:" + reportDescription;
        //Send notification to admins
    }
}
