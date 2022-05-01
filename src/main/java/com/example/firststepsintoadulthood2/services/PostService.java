package com.example.firststepsintoadulthood2.services;

import com.example.firststepsintoadulthood2.exceptions.*;
import com.example.firststepsintoadulthood2.model.Post;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

import static com.example.firststepsintoadulthood2.services.FileSystemService.getPathToFile;

public class PostService {

    private static List<Post> posts;
    private static final Path POSTS_PATH = getPathToFile("posts.json");


    public static void loadPostsFromFile() throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        if (!Files.exists(POSTS_PATH)) {

            FileUtils.copyURLToFile(Objects.requireNonNull(UserService.class.getClassLoader().getResource("prototype-posts.json")), POSTS_PATH.toFile());

        }


        posts = objectMapper.readValue(POSTS_PATH.toFile(), new TypeReference<List<Post>>() {});

    }


    public static void addPosts(String title, String description, String username) throws CouldNotWritePostsException {

        checkNullFieldsPosts(title);
        posts.add(new Post(title, description, username));
        persistPosts();

    }


    private static void checkNullFieldsPosts(String title){

        if(Objects.equals(title, "")){

            title = "Untitled";

        }

    }

    private static void persistPosts() throws CouldNotWritePostsException {
        try {

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(POSTS_PATH.toFile(), posts);

        } catch (IOException e) {

            throw new CouldNotWritePostsException();

        }
    }

    public static List<Post> getPostList(){

        return posts;

    }

}
