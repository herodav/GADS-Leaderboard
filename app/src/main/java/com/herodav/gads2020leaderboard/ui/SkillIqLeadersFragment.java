package com.herodav.gads2020leaderboard.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.herodav.gads2020leaderboard.model.Learner;
import com.herodav.gads2020leaderboard.R;

import java.util.List;

import static com.herodav.gads2020leaderboard.utils.LearnersCategory.HOURS;
import static com.herodav.gads2020leaderboard.utils.LearnersCategory.SKILL_IQ;
import static com.herodav.gads2020leaderboard.utils.Status.SUCCESS;

public class SkillIqLeadersFragment extends Fragment {


    private LearnersViewModel mViewModel;
    private RecyclerView mRecyclerView;
    private LeanerAdapter mAdapter;
    private List<Learner> mLearners;

    public static SkillIqLeadersFragment newInstance() {
        return new SkillIqLeadersFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.skill_iq_leaders_fragment, container, false);
        setupUi(v);
        mViewModel = ViewModelProviders.of(this).get(LearnersViewModel.class);
        mViewModel.getLearnersByCategory(SKILL_IQ).observe(getViewLifecycleOwner(), resource -> {
            if (resource.status == SUCCESS){
                mLearners = resource.data;
            }else {
                mLearners = null;
                Toast.makeText(getContext(), resource.message, Toast.LENGTH_LONG).show();
            }
            updateUI();
        });
        return v;
    }

    private void setupUi(View v) {
        mRecyclerView = v.findViewById(R.id.learners_rv);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void updateUI() {
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        if (mAdapter == null) {
            mAdapter = new LeanerAdapter(mLearners, getContext());
        }
        mAdapter.setLearners(mLearners);
        mRecyclerView.setAdapter(mAdapter);
    }

}