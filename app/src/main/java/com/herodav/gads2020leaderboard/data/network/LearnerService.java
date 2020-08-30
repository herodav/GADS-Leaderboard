package com.herodav.gads2020leaderboard.data.network;

import com.herodav.gads2020leaderboard.model.Learner;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

import static com.herodav.gads2020leaderboard.utils.Endpoints.LEARNING_LEADERS;
import static com.herodav.gads2020leaderboard.utils.Endpoints.SKILL_IQ_LEADERS;

public interface LearnerService {

    @GET(LEARNING_LEADERS)
    Call<List<Learner>> getLearningLeaders();

    @GET(SKILL_IQ_LEADERS)
    Call<List<Learner>> getSkillIqLeaders();
}
