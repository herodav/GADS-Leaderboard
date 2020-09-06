package com.herodav.gads2020leaderboard.data.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.herodav.gads2020leaderboard.model.Leader;
import com.herodav.gads2020leaderboard.data.db.entities.HoursLeader;
import com.herodav.gads2020leaderboard.data.db.entities.SkillIqLeader;

import java.util.List;

@Dao
public interface LearnerDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertLearner(Leader learner);

    @Query("SELECT * FROM HoursLeader ORDER BY hours DESC LIMIT 20")
    List<HoursLeader> getLearningHoursLeaders();

    @Query("SELECT * FROM si_leader ORDER BY score DESC LIMIT 20")
    List<SkillIqLeader> getSkillIQLeaders();

}
