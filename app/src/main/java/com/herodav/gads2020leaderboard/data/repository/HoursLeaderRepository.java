package com.herodav.gads2020leaderboard.data.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.MutableLiveData;

import com.herodav.gads2020leaderboard.data.db.LearnersDb;
import com.herodav.gads2020leaderboard.data.db.dao.HoursLeaderDao;
import com.herodav.gads2020leaderboard.data.db.entities.HoursLeader;
import com.herodav.gads2020leaderboard.data.network.HoursLeaderService;
import com.herodav.gads2020leaderboard.data.network.ServiceGenerator;
import com.herodav.gads2020leaderboard.model.DataResource;
import com.herodav.gads2020leaderboard.utils.TaskProgress;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.herodav.gads2020leaderboard.utils.Constants.DATABASE_ERROR;

public class HoursLeaderRepository {

    HoursLeaderDao mHoursLeaderDao;
    HoursLeaderService mHoursLeaderService;
    MutableLiveData<DataResource<List<HoursLeader>>> mHoursLeaders;

    public HoursLeaderRepository(Application application) {
        LearnersDb db = LearnersDb.getDatabase(application);
        mHoursLeaderDao = db.mHoursLeaderDao();
        mHoursLeaderService = ServiceGenerator.createService(HoursLeaderService.class);
        mHoursLeaders = new MutableLiveData<>();
    }

    public MutableLiveData<DataResource<List<HoursLeader>>> getHoursLeaders() {
        mHoursLeaderService.getLearningLeaders().enqueue(new Callback<List<HoursLeader>>() {
            @Override
            public void onResponse(Call<List<HoursLeader>> call, Response<List<HoursLeader>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        mHoursLeaders.setValue(DataResource.success(response.body()));
                        insertHoursLeaders(response.body());
                    }
                } else {
                    queryLearningHoursLeaders();
                }
            }

            @Override
            public void onFailure(Call<List<HoursLeader>> call, Throwable t) {
//                mLearningLeaders.setValue(DataResource.error(NETWORK_ERROR, null));
                queryLearningHoursLeaders();
            }
        });
        return mHoursLeaders;
    }

    void insertHoursLeaders(List<HoursLeader> leaders) {
        LearnersDb.databaseWriteExecutor.execute(() -> {
            for (HoursLeader leader : leaders) {
                mHoursLeaderDao.insert(leader);
            }
        });
    }

    private void queryLearningHoursLeaders() {
        new HoursLeadersTask(mHoursLeaderDao, (leaders) -> {
            if (leaders != null && leaders.size() > 0) {
                mHoursLeaders.setValue(DataResource.success(leaders));
            } else {
                mHoursLeaders.setValue(DataResource.error(DATABASE_ERROR, null));
            }
        }).execute();
    }

    static class HoursLeadersTask extends AsyncTask<Void, Void, List<HoursLeader>> {

        private HoursLeaderDao mHoursLeaderDao;
        private TaskProgress<HoursLeader> delegate;

        HoursLeadersTask(HoursLeaderDao dao, TaskProgress<HoursLeader> taskCompleted) {
            mHoursLeaderDao = dao;
            delegate = taskCompleted;
        }

        @Override
        protected List<HoursLeader> doInBackground(Void... voids) {
            return mHoursLeaderDao.getAll();
        }

        @Override
        protected void onPostExecute(List<HoursLeader> leaders) {
            delegate.onTaskCompleted(leaders);
        }
    }

}
