package com.herodav.gads2020leaderboard.data.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.herodav.gads2020leaderboard.data.db.entities.HoursLeader;

import java.util.List;

import static androidx.room.OnConflictStrategy.IGNORE;

@Dao
public interface HoursLeaderDao {

    @Insert(onConflict = IGNORE)
    void insert(HoursLeader leader);

    @Query("SELECT * FROM h_leader ORDER BY hours DESC LIMIT 20")
    List<HoursLeader> getAll();
}
