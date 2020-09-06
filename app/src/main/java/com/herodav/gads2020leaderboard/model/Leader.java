package com.herodav.gads2020leaderboard.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

/*@Entity(tableName = "learners_table",
        primaryKeys = {"name", "score", "hours", "country"})*/
public class Leader {

    @NonNull
    @SerializedName("name")
    private String name;

    @NonNull
    @SerializedName("country")
    private String country;

    @SerializedName("badgeUrl")
    private String badgeUrl;

    public Leader() {
        name = "";
        country = "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
