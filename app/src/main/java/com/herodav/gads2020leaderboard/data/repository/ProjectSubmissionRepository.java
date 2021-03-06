package com.herodav.gads2020leaderboard.data.repository;

import androidx.lifecycle.MutableLiveData;

import com.herodav.gads2020leaderboard.data.network.ProjectSubmissionService;
import com.herodav.gads2020leaderboard.data.network.ServiceGenerator;
import com.herodav.gads2020leaderboard.model.DataResource;
import com.herodav.gads2020leaderboard.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.herodav.gads2020leaderboard.utils.Constants.NETWORK_ERROR;
import static com.herodav.gads2020leaderboard.utils.Constants.SUCCESS;

public class ProjectSubmissionRepository {
    MutableLiveData<DataResource<String>> mSubmitResponse;

    public ProjectSubmissionRepository() {
        this.mSubmitResponse = new MutableLiveData<>();
    }

    public MutableLiveData<DataResource<String>> submit(User user) {
        ServiceGenerator.createService(ProjectSubmissionService.class)
                .submit(user.getEmail(),
                        user.getFirstName(),
                        user.getLastName(),
                        user.getRepoUrl()
                ).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    mSubmitResponse.setValue(DataResource.success(SUCCESS));
                } else {
                    mSubmitResponse.setValue(DataResource.error(NETWORK_ERROR, null));
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                mSubmitResponse.setValue(DataResource.error(NETWORK_ERROR, null));
            }
        });
        return mSubmitResponse;
    }
}
