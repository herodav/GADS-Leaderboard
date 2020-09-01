package com.herodav.gads2020leaderboard.data;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.MutableLiveData;

import com.herodav.gads2020leaderboard.data.db.LearnerDao;
import com.herodav.gads2020leaderboard.data.db.LearnersDb;
import com.herodav.gads2020leaderboard.data.network.LearnerService;
import com.herodav.gads2020leaderboard.data.network.ServiceGenerator;
import com.herodav.gads2020leaderboard.model.DataResource;
import com.herodav.gads2020leaderboard.model.Learner;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.herodav.gads2020leaderboard.utils.Constants.DATABASE_ERROR;

public class LearnerRepository {

    //add a select criteria that decides which list to retrieve. preferably a column in db

    private LearnerDao mLearnerDao;
    private LearnerService mLearnerService;
    MutableLiveData<DataResource<List<Learner>>> mLearningLeaders;
    MutableLiveData<DataResource<List<Learner>>> mSkillIqLeaders;

    public LearnerRepository(Application application) {
        LearnersDb db = LearnersDb.getDatabase(application);
        mLearnerDao = db.mLearnerDao();
        mLearningLeaders = new MutableLiveData<>();
        mSkillIqLeaders = new MutableLiveData<>();
        mLearnerService = ServiceGenerator.createService(LearnerService.class);
    }

    public MutableLiveData<DataResource<List<Learner>>> getLeaningLeaders() {
        mLearnerService.getLearningLeaders().enqueue(new Callback<List<Learner>>() {
            @Override
            public void onResponse(Call<List<Learner>> call, Response<List<Learner>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        mLearningLeaders.setValue(DataResource.success(response.body()));
                        insertLearners(response.body());
                    }
                } else {
                    queryLearningHoursLeaders();
                }
            }

            @Override
            public void onFailure(Call<List<Learner>> call, Throwable t) {
//                mLearningLeaders.setValue(DataResource.error(NETWORK_ERROR, null));
                queryLearningHoursLeaders();
            }
        });
        return mLearningLeaders;
    }


    public MutableLiveData<DataResource<List<Learner>>> getSkillIqLeaders() {
        mLearnerService.getSkillIqLeaders().enqueue(new Callback<List<Learner>>() {
            @Override
            public void onResponse(Call<List<Learner>> call, Response<List<Learner>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        mSkillIqLeaders.setValue(DataResource.success(response.body()));
                        insertLearners(response.body());
                    }
                } else {
                    querySkillIQLeaders();
                }
            }

            @Override
            public void onFailure(Call<List<Learner>> call, Throwable t) {
//                mSkillIqLeaders.setValue(DataResource.error(NETWORK_ERROR, null));
                querySkillIQLeaders();
            }
        });
        return mSkillIqLeaders;
    }

    private void queryLearningHoursLeaders() {
        new LearningHoursLeadersTask(mLearnerDao, (learners -> {
            if (learners != null && learners.size() > 0) {
                mLearningLeaders.setValue(DataResource.success(learners));
            } else {
                mLearningLeaders.setValue(DataResource.error(DATABASE_ERROR, null));
            }
        })).execute();
    }

    private void querySkillIQLeaders() {

        new SkillIqLeadersTask(mLearnerDao, (learners) -> {
            if (learners != null && learners.size() > 0) {
                mSkillIqLeaders.setValue(DataResource.success(learners));
            } else {
                mSkillIqLeaders.setValue(DataResource.error(DATABASE_ERROR, null));
            }
        }).execute();
    }

    void insertLearners(List<Learner> learners) {
        LearnersDb.databaseWriteExecutor.execute(() -> {
            for (Learner learner : learners) {
                mLearnerDao.insertLearner(learner);
            }
        });
    }

    static class LearningHoursLeadersTask extends AsyncTask<Void, Void, List<Learner>> {
        interface LearningHoursProcessFinished {
            void onProcessFinished(List<Learner> learners);
        }

        private LearnerDao mLearnerDao;
        private LearningHoursProcessFinished delegate;

        LearningHoursLeadersTask(LearnerDao dao, LearningHoursProcessFinished processFinished) {
            mLearnerDao = dao;
            delegate = processFinished;
        }

        @Override
        protected List<Learner> doInBackground(Void... voids) {
            return mLearnerDao.getLearningHoursLeaders();
        }

        @Override
        protected void onPostExecute(List<Learner> learners) {
            delegate.onProcessFinished(learners);
        }
    }

    static class SkillIqLeadersTask extends AsyncTask<Void, Void, List<Learner>> {

        interface SkillIQProcessFinished {
            void onProcessFinished(List<Learner> learners);
        }

        private LearnerDao mLearnerDao;
        private SkillIQProcessFinished delegate;

        SkillIqLeadersTask(LearnerDao dao, SkillIQProcessFinished processFinished) {
            mLearnerDao = dao;
            delegate = processFinished;
        }

        @Override
        protected List<Learner> doInBackground(Void... voids) {
            return mLearnerDao.getSkillIQLeaders();
        }

        @Override
        protected void onPostExecute(List<Learner> learners) {
            delegate.onProcessFinished(learners);
        }

    }
}
