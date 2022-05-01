package com.example.firststepsintoadulthood2.model;


public class Post {

    private String title;
    private String description;
    private String username;

    public Post(){


    }

    public Post(String title, String description, String username) {

        this.title = title;
        this.description = description;
        this.username = username;

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

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Post post = (Post) o;

        if (!title.equals(post.title)) return false;
        if (!description.equals(post.description)) return false;
        if (!username.equals(post.username)) return false;

        return true;
    }

    @Override
    public String toString() {
        return "Post{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", username='" + username + '\'' +
                '}';
    }

}
