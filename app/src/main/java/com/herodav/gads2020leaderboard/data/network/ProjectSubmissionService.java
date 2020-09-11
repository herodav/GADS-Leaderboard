package com.herodav.gads2020leaderboard.data.network;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

import static com.herodav.gads2020leaderboard.utils.Endpoints.GOOGLE_FORM_URL;

public interface ProjectSubmissionService {
    @FormUrlEncoded()
    @POST(GOOGLE_FORM_URL)
    Call<Void> submit(
            @Field("entry.1824927963") String emailAddress,
            @Field("entry.1877115667") String firstName,
            @Field("entry.2006916086") String lastName,
            @Field("entry.284483984") String projectUrl
    );
}
