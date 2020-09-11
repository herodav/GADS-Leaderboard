package com.herodav.gads2020leaderboard.ui.skillIqLeader;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.herodav.gads2020leaderboard.data.db.entities.SkillIqLeader;
import com.herodav.gads2020leaderboard.data.repository.SkillIqLeaderRepository;
import com.herodav.gads2020leaderboard.model.DataResource;

import java.util.List;

public class SkillIqLeaderViewModel extends AndroidViewModel {

    SkillIqLeaderRepository mRepository;

    public SkillIqLeaderViewModel(@NonNull Application application) {
        super(application);
        mRepository = new SkillIqLeaderRepository(application);
    }

    MutableLiveData<DataResource<List<SkillIqLeader>>> getSkillIqLeaders() {
        return mRepository.getSkillIqLeaders();
    }
}
