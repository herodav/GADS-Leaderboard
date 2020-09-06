package com.herodav.gads2020leaderboard.data.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.herodav.gads2020leaderboard.data.db.entities.SkillIqLeader;

import java.util.List;

import static androidx.room.OnConflictStrategy.IGNORE;

@Dao
public interface SkillIqLeaderDao {
    @Insert(onConflict = IGNORE)
    void insert(SkillIqLeader leader);

    @Query("SELECT * FROM si_leader ORDER BY score DESC LIMIT 20")
    List<SkillIqLeader> getAll();
}
