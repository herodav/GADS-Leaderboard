package com.herodav.gads2020leaderboard.model;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("firstName")
    private String firstName;
    @SerializedName("lastName")
    private String lastName;
    @SerializedName("email")
    private String email;
    @SerializedName("url")
    private String repoUrl;

    public User(String firstName, String lastName, String email, String repoUrl) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.repoUrl = repoUrl;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRepoUrl() {
        return repoUrl;
    }

    public void setRepoUrl(String repoUrl) {
        this.repoUrl = repoUrl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
