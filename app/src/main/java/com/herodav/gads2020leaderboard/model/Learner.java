package com.herodav.gads2020leaderboard.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "learners_table",
        primaryKeys = {"name", "score", "hours", "country"})
public class Learner {

    @NonNull
    @SerializedName("name")
    private String name;

    @SerializedName("score")
    @ColumnInfo(name = "score", defaultValue = "0")
    private int score;

    @SerializedName("hours")
    @ColumnInfo(name = "hours", defaultValue = "0")
    private int hours;

    @NonNull
    @SerializedName("country")
    private String country;

    @SerializedName("badgeUrl")
    private String badgeUrl;

    public Learner() {
        name = "";
        country = "";
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
