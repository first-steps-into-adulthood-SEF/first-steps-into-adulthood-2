package com.example.firststepsintoadulthood2.services;

import com.example.firststepsintoadulthood2.exceptions.*;
import com.example.firststepsintoadulthood2.model.Post;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Stack;

import static com.example.firststepsintoadulthood2.services.FileSystemService.getPathToFile;

public class ReportedPostsService {

    private static Stack<Post> reportedPosts;
    private static final Path REPORTED_POSTS_PATH = getPathToFile("reported-posts.json");



    public static void loadReportedPostsFromFile() throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        if (!Files.exists(REPORTED_POSTS_PATH)) {

            FileUtils.copyURLToFile(Objects.requireNonNull(UserService.class.getClassLoader().getResource("proto-reports.json")), REPORTED_POSTS_PATH.toFile());

        }


        reportedPosts = objectMapper.readValue(REPORTED_POSTS_PATH.toFile(), new TypeReference<Stack<Post>>() {});

    }


    public static void addReportedPosts(String title, String description, String username, String date, String option) throws CouldNotWritePostsException {

        reportedPosts.push(new Post(title, description, username, date, option));
        persistReportedPosts();

    }



    private static void persistReportedPosts() throws CouldNotWritePostsException {
        try {

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(REPORTED_POSTS_PATH.toFile(), reportedPosts);

        } catch (IOException e) {

            throw new CouldNotWritePostsException();

        }
    }

}
