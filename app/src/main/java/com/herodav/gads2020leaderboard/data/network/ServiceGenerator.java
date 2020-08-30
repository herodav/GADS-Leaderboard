package com.herodav.gads2020leaderboard.data.network;

import com.google.gson.Gson;
import com.herodav.gads2020leaderboard.utils.Endpoints;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
    public static <S> S createService(Class<S> serviceClass) {
        return new Retrofit.Builder()
                .baseUrl(Endpoints.BASE_URL)
                .client(createOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build()
                .create(serviceClass);
    }

    private static OkHttpClient createOkHttpClient() {

        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS);
        return builder.build();
    }
}
