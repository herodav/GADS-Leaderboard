package com.herodav.gads2020leaderboard.data.network;

import com.herodav.gads2020leaderboard.data.db.entities.HoursLeader;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

import static com.herodav.gads2020leaderboard.utils.Endpoints.LEARNING_LEADERS;

public interface HoursLeaderService {
    @GET(LEARNING_LEADERS)
    Call<List<HoursLeader>> getLearningLeaders();
}
