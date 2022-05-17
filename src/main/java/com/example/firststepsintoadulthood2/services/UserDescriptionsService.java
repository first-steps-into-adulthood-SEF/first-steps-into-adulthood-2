package com.example.firststepsintoadulthood2.services;

import com.example.firststepsintoadulthood2.model.Bios;
import com.example.firststepsintoadulthood2.model.User;
import com.example.firststepsintoadulthood2.exceptions.CouldNotWriteUsersException;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import static com.example.firststepsintoadulthood2.services.FileSystemService.getPathToFile;


public class UserDescriptionsService {

    private static List<Bios> userBios;
    private static final Path PATHS = getPathToFile("users-profile-descriptions.json");

    public static void loadUserDescriptionsFromFile() throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        if (!Files.exists(PATHS)) {

            FileUtils.copyURLToFile(Objects.requireNonNull(UserService.class.getClassLoader().getResource("prototype-profile.json")), PATHS.toFile());

        }


        userBios = objectMapper.readValue(PATHS.toFile(), new TypeReference<List<Bios>>() {});

    }

    public static void addUser(String username, String profileDescription) throws CouldNotWriteUsersException {

        userBios.add(new Bios(username, profileDescription));
        persistUsers();

    }



    private static void persistUsers() throws CouldNotWriteUsersException {
        try {

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(PATHS.toFile(), userBios);

        } catch (IOException e) {

            throw new CouldNotWriteUsersException();

        }
    }


}
