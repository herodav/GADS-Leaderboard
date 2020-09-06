package com.herodav.gads2020leaderboard.ui;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.herodav.gads2020leaderboard.data.db.entities.HoursLeader;
import com.herodav.gads2020leaderboard.data.repository.HoursLeaderRepository;
import com.herodav.gads2020leaderboard.model.DataResource;

import java.util.List;

public class HoursLeaderViewModel extends AndroidViewModel {

    HoursLeaderRepository mRepository;

    public HoursLeaderViewModel(@NonNull Application application) {
        super(application);
        mRepository = new HoursLeaderRepository(application);
    }

    public MutableLiveData<DataResource<List<HoursLeader>>> getHoursLeaders(){
        return mRepository.getHoursLeaders();
    }
}
