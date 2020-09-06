package com.herodav.gads2020leaderboard.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.herodav.gads2020leaderboard.model.Leader;

import java.util.List;

public class LeaderAdapter<T extends Leader> extends RecyclerView.Adapter<LeaderHolder> {
    private List<T> mLeaders;
    private Context mContext;

    public LeaderAdapter(List<T> leaders, Context context) {
        mLeaders = leaders;
        mContext = context;
    }

    @NonNull
    @Override
    public LeaderHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LeaderHolder(LayoutInflater.from(mContext), parent);
    }

    @Override
    public void onBindViewHolder(@NonNull LeaderHolder holder, int position) {
        T leader = mLeaders.get(position);
        holder.bind(leader);
    }

    @Override
    public int getItemCount() {
        return mLeaders.size();
    }

    public void setLeaders(List<T> leaders) {
        mLeaders = leaders;
    }
}

