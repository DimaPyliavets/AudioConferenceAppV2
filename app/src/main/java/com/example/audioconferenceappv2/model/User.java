package com.example.audioconferenceappv2.model;

public class User {

    private static String id;
    String email;
    String username;
    String imageURL;

    public User(String id, String username, String imageURL, String email) {
        this.id = id;
        this.username = username;
        this.imageURL = imageURL;
        this.email = email;
    }

    public User(){ }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String id) {
        this.email = email;
    }
}
