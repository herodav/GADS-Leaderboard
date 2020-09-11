package com.herodav.gads2020leaderboard.ui.projectSubmission;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.herodav.gads2020leaderboard.data.repository.ProjectSubmissionRepository;
import com.herodav.gads2020leaderboard.model.DataResource;
import com.herodav.gads2020leaderboard.model.User;

public class ProjectSubmissionViewModel extends AndroidViewModel {
    private ProjectSubmissionRepository mRepository;
    private MutableLiveData<User> mUser;

    public ProjectSubmissionViewModel(@NonNull Application application) {
        super(application);
        mRepository = new ProjectSubmissionRepository();
        mUser = new MutableLiveData<>();
    }

    public MutableLiveData<DataResource<String>> submit(User user) {
        return mRepository.submit(user);
    }

    public MutableLiveData<User> getUser() {
        return mUser;
    }

    public void setUser(User user) {
        mUser.setValue(user);
    }
}