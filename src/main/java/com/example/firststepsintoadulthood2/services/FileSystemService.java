package com.example.firststepsintoadulthood2.services;

import com.example.firststepsintoadulthood2.Main;

import java.nio.file.Path;
import java.nio.file.Paths;



public class FileSystemService {

    private static final String APPLICATION_FOLDER = "folder_json";
    private static final String USER_FOLDER = "src/main/resources";
    public static final Path APPLICATION_HOME_PATH = Paths.get(USER_FOLDER, APPLICATION_FOLDER);

    public static Path getPathToFile(String... path) {

        return APPLICATION_HOME_PATH.resolve(Paths.get("", path));

    }

}
