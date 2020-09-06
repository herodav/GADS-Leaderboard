package com.herodav.gads2020leaderboard.ui;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.herodav.gads2020leaderboard.R;
import com.herodav.gads2020leaderboard.data.db.entities.HoursLeader;
import com.herodav.gads2020leaderboard.data.db.entities.SkillIqLeader;
import com.herodav.gads2020leaderboard.model.Leader;
import com.squareup.picasso.Picasso;

import java.util.Locale;

public class LeaderHolder<T> extends RecyclerView.ViewHolder {
    private ImageView imgBadge;
    private TextView tvName, tvDetail;

    public LeaderHolder(LayoutInflater inflater, ViewGroup parent) {
        super(inflater.inflate(R.layout.leader_item, parent, false));

        imgBadge = (ImageView) itemView.findViewById(R.id.img_leaner_item_badge);
        tvName = (TextView) itemView.findViewById(R.id.tv_leaner_item_name);
        tvDetail = (TextView) itemView.findViewById(R.id.tv_leaner_item_detail);
    }

    public void bind(T leader) {
        String detail = "";
        if (leader instanceof SkillIqLeader) {
            detail = String.format(Locale.getDefault(), "%d skill IQ score, %s",
                    ((SkillIqLeader) leader).getScore(), ((SkillIqLeader) leader).getCountry());
        }

        if (leader instanceof HoursLeader) {
            detail = String.format(Locale.getDefault(), "%d learning hours, %s",
                    ((HoursLeader) leader).getHours(), ((HoursLeader) leader).getCountry());
        }

        tvName.setText(((Leader) leader).getName());
        tvDetail.setText(detail);
        Picasso.get()
                .load(((Leader) leader).getBadgeUrl())
                .placeholder(android.R.drawable.ic_menu_report_image)
                .error(android.R.drawable.ic_menu_report_image)
                .into(imgBadge);
    }
}

