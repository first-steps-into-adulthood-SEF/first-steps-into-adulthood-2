package com.example.firststepsintoadulthood2.model;


import java.util.*;

public class Post {

    private String title;
    private String description;
    private String username;
    private String date;
    private String option;
    public ArrayList<String> replies = new ArrayList<>();

    public Post(){

    }


    public Post(String title, String description, String username, String date) {

        this.title = title;
        this.description = description;
        this.username = username;
        this.date = date;
    }

    public Post(String title, String description, String username, String date, String option) {

        this.title = title;
        this.description = description;
        this.username = username;
        this.date = date;
        this.option = option;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public ArrayList<String> getReplies() { return replies;}

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Post post = (Post) o;

        if (!title.equals(post.title)) return false;
        if (!description.equals(post.description)) return false;
        if (!username.equals(post.username)) return false;
        if (!date.equals(post.date)) return false;
        if (!option.equals(post.option)) return false;

        return true;
    }

    @Override
    public String toString() {
        return "Post{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", username='" + username + '\'' +
                ", date='" + date + '\'' +
                ", option='" + option + '\'' +
                '}';
    }
}
