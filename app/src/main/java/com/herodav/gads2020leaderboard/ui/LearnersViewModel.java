package com.herodav.gads2020leaderboard.ui;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.herodav.gads2020leaderboard.Model.Learner;

import java.util.ArrayList;
import java.util.List;

public class LearnersViewModel extends AndroidViewModel {

    MutableLiveData<List<Learner>> mLearners;

    public LearnersViewModel(@NonNull Application application) {
        super(application);
        mLearners = new MutableLiveData<>();

        List<Learner> learners = new ArrayList<>();
        learners.add(new Learner("Hero", "30", ""));
        learners.add(new Learner("Kabamba", "43", ""));
        learners.add(new Learner("Mbiya", "Wa 56", ""));
        learners.add(new Learner("The King", "56", ""));
        learners.add(new Learner("Dav", "09", ""));
        learners.add(new Learner("kabuya", "76", ""));
        learners.add(new Learner("Charly", "23", ""));
        learners.add(new Learner("Maximilien", "45", ""));
        learners.add(new Learner("Franc", "6", ""));
        learners.add(new Learner("Maestro", "00", ""));
        learners.add(new Learner("Heloda", "87", ""));
        learners.add(new Learner("King", "4", ""));
        learners.add(new Learner("Gaylord", "35", ""));
        learners.add(new Learner("Arnold", "56", ""));
        learners.add(new Learner("Vanessa", "56", ""));
        learners.add(new Learner("Judith", "78", ""));
        learners.add(new Learner("Jacqiue", "23", ""));
        learners.add(new Learner("Manyonga", "56", ""));
        learners.add(new Learner("Vena", "78", ""));
        learners.add(new Learner("Fernand", "249", ""));
        learners.add(new Learner("Rachel", "300", ""));

        mLearners.setValue(learners);
    }

    public MutableLiveData<List<Learner>> getLearners() {
        return mLearners;
    }
}