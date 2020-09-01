package com.herodav.gads2020leaderboard.data.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.herodav.gads2020leaderboard.model.Learner;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Learner.class}, version = 1, exportSchema = false)
public abstract class LearnersDb extends RoomDatabase {

    public abstract LearnerDao mLearnerDao();

    private static volatile LearnersDb INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    //use to run database operations asynchronously on a background thread.
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static LearnersDb getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (LearnersDb.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            LearnersDb.class, "learners_db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
