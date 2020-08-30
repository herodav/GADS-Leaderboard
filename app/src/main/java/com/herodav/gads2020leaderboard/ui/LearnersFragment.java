package com.herodav.gads2020leaderboard.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.herodav.gads2020leaderboard.Model.Learner;
import com.herodav.gads2020leaderboard.R;

import java.util.List;

public class LearnersFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private LeanerAdapter mAdapter;
    private List<Learner> mLearners;
    private int layoutResourceId;

    public LearnersFragment(int layoutId) {
        layoutResourceId = layoutId;


    }


    public void setupRecyclerView() {
        if (mAdapter == null) {
            mAdapter = new LeanerAdapter(mLearners, getContext());
        }
        mAdapter.setLearners(mLearners);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void setLearners(List<Learner> learners) {
        mLearners = learners;
    }

    public int getLayoutResourceId() {
        return layoutResourceId;
    }
}
