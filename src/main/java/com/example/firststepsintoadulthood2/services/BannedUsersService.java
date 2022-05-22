package com.example.firststepsintoadulthood2.services;

import com.example.firststepsintoadulthood2.exceptions.CouldNotWriteUsersException;
import com.example.firststepsintoadulthood2.model.BannedUser;
import com.example.firststepsintoadulthood2.model.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Date;
import java.util.Objects;
import java.util.Stack;

import static com.example.firststepsintoadulthood2.services.FileSystemService.getPathToFile;

public class BannedUsersService {

    private static Stack<BannedUser> bannedUsers;
    private static final Path BANNED_USERS_PATH = getPathToFile("banned-users.json");

    public static void loadBannedUsersFromFile() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        if (!Files.exists(BANNED_USERS_PATH)) {
            FileUtils.copyURLToFile(Objects.requireNonNull(UserService.class.getClassLoader().getResource("proto-reports.json")), BANNED_USERS_PATH.toFile());
        }
        bannedUsers = objectMapper.readValue(BANNED_USERS_PATH.toFile(), new TypeReference<Stack<BannedUser>>() {});
    }


    public static void addBannedUser(String username, String banMessage, int banPeriod) throws CouldNotWriteUsersException {
        bannedUsers.push(new BannedUser(username, banMessage, banPeriod));
        persistBannedUsers();
    }


    private static void persistBannedUsers() throws CouldNotWriteUsersException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(BANNED_USERS_PATH.toFile(), bannedUsers);
        } catch (IOException e) {
            throw new CouldNotWriteUsersException();
        }
    }

    public static long checkBanned(String username) throws IOException {
        loadBannedUsersFromFile();
        long remainingTime = 0;
        for(BannedUser b : bannedUsers){
            if(b.getUsername().equals(username) && b.getBanDate()!=0){
                long currentTime = System.currentTimeMillis();
                if(currentTime - b.getBanDate() < (long) b.getBanPeriod() * 86400000){
                    remainingTime = (long) b.getBanPeriod() * 86400000 - (currentTime - b.getBanDate());
                }
                if(remainingTime < 0){
                    b.setBanPeriod(0);
                }
            }
        }
        return remainingTime;
    }

    public static String getBanDescription(String username){
        String description = null;
        for(BannedUser b : bannedUsers){
            if(b.getUsername().equals(username) && b.getBanDate()!=0){
                description = b.getBanMessage();
            }
        }
        return description;
    }
}
