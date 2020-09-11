package com.herodav.gads2020leaderboard.ui;

import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.herodav.gads2020leaderboard.R;
import com.herodav.gads2020leaderboard.model.Leader;

import java.util.List;

public class LeaderListFragment<T extends Leader> extends Fragment {

    private RecyclerView mRecyclerView;
    private LeaderAdapter<T> mAdapter;
    private List<T> mLeaders;

    public void setupUi(View v) {
        mRecyclerView = v.findViewById(R.id.learners_rv);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    public void updateUI() {
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        if (mAdapter == null) {
            mAdapter = new LeaderAdapter<T>(mLeaders, getContext());
        }

        mAdapter.setLeaders(mLeaders);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void setLeaders(List<T> leaders) {
        mLeaders = leaders;
    }
}
