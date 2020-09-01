package com.herodav.gads2020leaderboard.ui;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.herodav.gads2020leaderboard.R;
import com.herodav.gads2020leaderboard.model.Learner;
import com.squareup.picasso.Picasso;

import java.util.Locale;

public class LeanerHolder extends RecyclerView.ViewHolder {
    private ImageView imgBadge;
    private TextView tvName, tvDetail;

    public LeanerHolder(LayoutInflater inflater, ViewGroup parent) {
        super(inflater.inflate(R.layout.learner_item, parent, false));

        imgBadge = (ImageView) itemView.findViewById(R.id.img_leaner_item_badge);
        tvName = (TextView) itemView.findViewById(R.id.tv_leaner_item_name);
        tvDetail = (TextView) itemView.findViewById(R.id.tv_leaner_item_detail);
    }

    public void bind(Learner learner) {
        String detail = "";
        if (learner.getScore() > 0) {
            detail = String.format(Locale.getDefault(), "%d skill IQ score, %s",
                    learner.getScore(), learner.getCountry());
        }
        if (learner.getHours() > 0) {
            detail = String.format(Locale.getDefault(), "%d learning hours, %s",
                    learner.getHours(), learner.getCountry());
        }
        tvName.setText(learner.getName());
        tvDetail.setText(detail);
        Picasso.get()
                .load(learner.getBadgeUrl())
                .placeholder(android.R.drawable.ic_menu_report_image)
                .error(android.R.drawable.ic_menu_report_image)//todo: get the badge from res
                .into(imgBadge);
    }
}

