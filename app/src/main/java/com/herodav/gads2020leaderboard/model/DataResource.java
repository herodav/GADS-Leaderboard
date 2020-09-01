package com.herodav.gads2020leaderboard.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.herodav.gads2020leaderboard.utils.Status;

public class DataResource<T> {

    @NonNull
    public final Status status;
    @Nullable
    public final T data;
    @Nullable
    public final String message;

    private DataResource(@NonNull Status status, @Nullable T data, @Nullable String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }


    public static <T> DataResource<T> success(@NonNull T data) {
        return new DataResource<>(Status.SUCCESS, data, null);
    }

    public static <T> DataResource<T> error(String msg, @Nullable T data) {
        return new DataResource<>(Status.ERROR, data, msg);
    }
}
