package com.herodav.gads2020leaderboard.data.db.entities;

import androidx.room.Entity;

import com.google.gson.annotations.SerializedName;
import com.herodav.gads2020leaderboard.model.Leader;

@Entity(tableName = "h_leader", primaryKeys = {"name", "hours", "country"})
public class HoursLeader extends Leader {

    @SerializedName("hours")
    private int hours;

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }
}
