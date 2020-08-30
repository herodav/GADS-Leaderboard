package com.herodav.gads2020leaderboard.data;

import androidx.lifecycle.MutableLiveData;

import com.herodav.gads2020leaderboard.data.network.LearnerService;
import com.herodav.gads2020leaderboard.data.network.ServiceGenerator;
import com.herodav.gads2020leaderboard.model.Learner;
import com.herodav.gads2020leaderboard.model.NetworkResource;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.herodav.gads2020leaderboard.utils.Constants.NETWORK_ERROR;

public class LearnerRepository {

    MutableLiveData<NetworkResource<List<Learner>>> mLearningLeaders;
    MutableLiveData<NetworkResource<List<Learner>>> mSkillIqLeaders;
    private LearnerService mLearnerService;

    public LearnerRepository() {
        mLearningLeaders = new MutableLiveData<>();
        mSkillIqLeaders = new MutableLiveData<>();
        mLearnerService = ServiceGenerator.createService(LearnerService.class);
    }

    public MutableLiveData<NetworkResource<List<Learner>>> getLeaningLeaders() {
        mLearnerService.getLearningLeaders().enqueue(new Callback<List<Learner>>() {
            @Override
            public void onResponse(Call<List<Learner>> call, Response<List<Learner>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        mLearningLeaders.setValue(NetworkResource.success(response.body()));
                    }
                } else {
                    mLearningLeaders.setValue(NetworkResource.error(NETWORK_ERROR, null));
                }
            }

            @Override
            public void onFailure(Call<List<Learner>> call, Throwable t) {
                mLearningLeaders.setValue(NetworkResource.error(NETWORK_ERROR, null));
            }
        });
        return mLearningLeaders;
    }

    public MutableLiveData<NetworkResource<List<Learner>>> getSkillIqLeaders() {
        mLearnerService.getSkillIqLeaders().enqueue(new Callback<List<Learner>>() {
            @Override
            public void onResponse(Call<List<Learner>> call, Response<List<Learner>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        mLearningLeaders.setValue(NetworkResource.success(response.body()));
                    }
                } else {
                    mLearningLeaders.setValue(NetworkResource.error(NETWORK_ERROR, null));
                }
            }

            @Override
            public void onFailure(Call<List<Learner>> call, Throwable t) {
                mLearningLeaders.setValue(NetworkResource.error(NETWORK_ERROR, null));
            }
        });
        return mLearningLeaders;
    }
}
