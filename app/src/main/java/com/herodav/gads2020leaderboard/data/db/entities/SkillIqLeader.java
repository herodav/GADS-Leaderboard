package com.herodav.gads2020leaderboard.data.db.entities;

import androidx.room.Entity;

import com.google.gson.annotations.SerializedName;
import com.herodav.gads2020leaderboard.model.Leader;

@Entity(tableName = "si_leader", primaryKeys = {"name", "score", "country"})
public class SkillIqLeader extends Leader {

    @SerializedName("score")
    private int score;

    public SkillIqLeader() {
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

}
