package com.herodav.gads2020leaderboard.ui;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.herodav.gads2020leaderboard.Model.Learner;
import com.herodav.gads2020leaderboard.R;

public class LeanerHolder extends RecyclerView.ViewHolder {
    private ImageView imgBadge;
    private TextView tvName, tvScore;

    public LeanerHolder(LayoutInflater inflater, ViewGroup parent) {
        super(inflater.inflate(R.layout.learner_item, parent, false));

        imgBadge = (ImageView) itemView.findViewById(R.id.img_leaner_item_badge);
        tvName = (TextView) itemView.findViewById(R.id.tv_leaner_item_name);
        tvScore = (TextView) itemView.findViewById(R.id.tv_leaner_item_score);
    }

    public void bind(Learner learner) {
        tvName.setText(learner.getName());
        tvScore.setText(learner.getScore());

    }
}

