package com.herodav.gads2020leaderboard.data.network;

import com.herodav.gads2020leaderboard.data.db.entities.SkillIqLeader;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

import static com.herodav.gads2020leaderboard.utils.Endpoints.SKILL_IQ_LEADERS;

public interface SkillIqLeaderService {

    @GET(SKILL_IQ_LEADERS)
    Call<List<SkillIqLeader>> getSkillIqLeaders();
}
