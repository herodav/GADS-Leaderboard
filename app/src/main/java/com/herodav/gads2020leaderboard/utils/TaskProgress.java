package com.herodav.gads2020leaderboard.utils;

import java.util.List;

public interface TaskProgress<T> {
    void onTaskCompleted(List<T> result);
}
