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
import java.util.Stack;

import static com.example.firststepsintoadulthood2.services.FileSystemService.getPathToFile;

public class PostService {

    private static Stack<Post> posts;
    private static final Path POSTS_PATH = getPathToFile("posts.json");



    public static void loadPostsFromFile() throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        if (!Files.exists(POSTS_PATH)) {

            FileUtils.copyURLToFile(Objects.requireNonNull(UserService.class.getClassLoader().getResource("prototype-posts.json")), POSTS_PATH.toFile());

        }


        posts = objectMapper.readValue(POSTS_PATH.toFile(), new TypeReference<Stack<Post>>() {});

    }


    public static void addPosts(String title, String description, String username, String date) throws CouldNotWritePostsException {

        title = checkNullFieldsPosts(title);
        posts.push(new Post(title, description, username, date));
        persistPosts();

    }


    private static String checkNullFieldsPosts(String title){

        if(Objects.equals(title, "")){

            title = "(Untitled)";

        }

        return title;

    }

    private static void persistPosts() throws CouldNotWritePostsException {
        try {

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(POSTS_PATH.toFile(), posts);

        } catch (IOException e) {

            throw new CouldNotWritePostsException();

        }
    }

    public static void addReplyToPost(String postTitle, String author, String replier, String reply){
        for(Post post : posts){
            if(post.getTitle().equals(postTitle) && post.getUsername().equals(author)){
                post.getReplies().add(replier + " : " + reply);
            }
        }
    }

    public static Stack<Post> getPostList(){

        return posts;

    }

}
