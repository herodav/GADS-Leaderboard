package com.herodav.gads2020leaderboard.ui;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.herodav.gads2020leaderboard.data.LearnerRepository;
import com.herodav.gads2020leaderboard.model.Learner;
import com.herodav.gads2020leaderboard.model.NetworkResource;
import com.herodav.gads2020leaderboard.utils.LearnersCategory;

import java.util.List;

import static com.herodav.gads2020leaderboard.utils.LearnersCategory.HOURS;

public class LearnersViewModel extends AndroidViewModel {

    LearnerRepository mLearnerRepository;

    public LearnersViewModel(@NonNull Application application) {
        super(application);
        mLearnerRepository = new LearnerRepository();
    }

    public MutableLiveData<NetworkResource<List<Learner>>> getLearnersByCategory(LearnersCategory category) {
        return category.equals(HOURS) ? mLearnerRepository.getLeaningLeaders()
                : mLearnerRepository.getSkillIqLeaders();
    }
}