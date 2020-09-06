package com.herodav.gads2020leaderboard.data.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.MutableLiveData;

import com.herodav.gads2020leaderboard.data.db.LearnersDb;
import com.herodav.gads2020leaderboard.data.db.dao.SkillIqLeaderDao;
import com.herodav.gads2020leaderboard.data.db.entities.SkillIqLeader;
import com.herodav.gads2020leaderboard.data.network.ServiceGenerator;
import com.herodav.gads2020leaderboard.data.network.SkillIqLeaderService;
import com.herodav.gads2020leaderboard.model.DataResource;
import com.herodav.gads2020leaderboard.utils.TaskProgress;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.herodav.gads2020leaderboard.utils.Constants.DATABASE_ERROR;

public class SkillIqLeaderRepository {
    SkillIqLeaderDao mSkillIqLeaderDao;
    SkillIqLeaderService mSkillIqLeaderService;
    MutableLiveData<DataResource<List<SkillIqLeader>>> mSkillIqLeaders;

    public SkillIqLeaderRepository(Application application) {
        LearnersDb db = LearnersDb.getDatabase(application);
        mSkillIqLeaderDao = db.mSkillIqLeaderDao();
        mSkillIqLeaderService = ServiceGenerator.createService(SkillIqLeaderService.class);
        mSkillIqLeaders = new MutableLiveData<>();
    }

    public MutableLiveData<DataResource<List<SkillIqLeader>>> getSkillIqLeaders() {
        mSkillIqLeaderService.getSkillIqLeaders().enqueue(new Callback<List<SkillIqLeader>>() {
            @Override
            public void onResponse(Call<List<SkillIqLeader>> call, Response<List<SkillIqLeader>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        mSkillIqLeaders.setValue(DataResource.success(response.body()));
                        insertSkillIqLeaders(response.body());
                    }
                } else {
                    queryLearningSkillIqLeaders();
                }
            }

            @Override
            public void onFailure(Call<List<SkillIqLeader>> call, Throwable t) {
//                mLearningLeaders.setValue(DataResource.error(NETWORK_ERROR, null));
                queryLearningSkillIqLeaders();
            }
        });
        return mSkillIqLeaders;
    }

    void insertSkillIqLeaders(List<SkillIqLeader> leaders) {
        LearnersDb.databaseWriteExecutor.execute(() -> {
            for (SkillIqLeader leader : leaders) {
                mSkillIqLeaderDao.insert(leader);
            }
        });
    }

    private void queryLearningSkillIqLeaders() {
        new SkillIqLeaderRepository.SkillIqLeadersTask(mSkillIqLeaderDao, (leaders) -> {
            if (leaders != null && leaders.size() > 0) {
                mSkillIqLeaders.setValue(DataResource.success(leaders));
            } else {
                mSkillIqLeaders.setValue(DataResource.error(DATABASE_ERROR, null));
            }
        }).execute();
    }

    static class SkillIqLeadersTask extends AsyncTask<Void, Void, List<SkillIqLeader>> {

        private SkillIqLeaderDao mSkillIqLeaderDao;
        private TaskProgress<SkillIqLeader> delegate;

        SkillIqLeadersTask(SkillIqLeaderDao dao, TaskProgress<SkillIqLeader> taskCompleted) {
            mSkillIqLeaderDao = dao;
            delegate = taskCompleted;
        }

        @Override
        protected List<SkillIqLeader> doInBackground(Void... voids) {
            return mSkillIqLeaderDao.getAll();
        }

        @Override
        protected void onPostExecute(List<SkillIqLeader> leaders) {
            delegate.onTaskCompleted(leaders);
        }
    }

}
