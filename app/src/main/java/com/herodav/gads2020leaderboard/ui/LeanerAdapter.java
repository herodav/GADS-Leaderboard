package com.herodav.gads2020leaderboard.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.herodav.gads2020leaderboard.model.Learner;

import java.util.List;

public class LeanerAdapter extends RecyclerView.Adapter<LeanerHolder> {
    private List<Learner> mLearners;
    private Context mContext;

    public LeanerAdapter(List<Learner> learners, Context context) {
        mLearners = learners;
        mContext = context;
    }

    @NonNull
    @Override
    public LeanerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LeanerHolder(LayoutInflater.from(mContext), parent);
    }

    @Override
    public void onBindViewHolder(@NonNull LeanerHolder holder, int position) {
        Learner learner = mLearners.get(position);
        holder.bind(learner);
    }

    @Override
    public int getItemCount() {
        return mLearners.size();
    }

    public void setLearners(List<Learner> learners) {
        mLearners = learners;
    }
}

