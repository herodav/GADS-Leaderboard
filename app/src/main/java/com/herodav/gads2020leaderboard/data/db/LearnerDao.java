package com.herodav.gads2020leaderboard.data.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.herodav.gads2020leaderboard.model.Learner;

import java.util.List;

@Dao
public interface LearnerDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertLearner(Learner learner);

    @Query("SELECT * FROM learners_table WHERE hours > 0 ORDER BY hours DESC LIMIT 20")
    List<Learner> getLearningHoursLeaders();

    @Query("SELECT * FROM learners_table WHERE score > 0 ORDER BY score DESC LIMIT 20")
    List<Learner> getSkillIQLeaders();

}
