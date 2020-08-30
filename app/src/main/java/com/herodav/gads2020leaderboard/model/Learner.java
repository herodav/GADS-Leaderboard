package com.herodav.gads2020leaderboard.model;

import com.google.gson.annotations.SerializedName;

public class Learner {

    @SerializedName("name")
    private String name;
    @SerializedName("score")
    private int score;
    @SerializedName("hours")
    private int hours;
    @SerializedName("badgeUrl")
    private String badgeUrl;
    @SerializedName("country")
    private String country;

    public Learner() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getBadgeUrl() {
        return badgeUrl;
    }

    public void setBadgeUrl(String badgeUrl) {
        this.badgeUrl = badgeUrl;
    }
}
