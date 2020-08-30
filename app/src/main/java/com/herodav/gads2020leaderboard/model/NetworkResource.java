package com.herodav.gads2020leaderboard.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.herodav.gads2020leaderboard.utils.Status;

public class NetworkResource<T> {

    @NonNull
    public final Status status;
    @Nullable
    public final T data;
    @Nullable
    public final String message;

    private NetworkResource(@NonNull Status status, @Nullable T data, @Nullable String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }


    public static <T> NetworkResource<T> success(@NonNull T data) {
        return new NetworkResource<>(Status.SUCCESS, data, null);
    }

    public static <T> NetworkResource<T> error(String msg, @Nullable T data) {
        return new NetworkResource<>(Status.ERROR, data, msg);
    }
}
