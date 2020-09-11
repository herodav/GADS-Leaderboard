package com.herodav.gads2020leaderboard.ui.hoursLeader;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.herodav.gads2020leaderboard.R;
import com.herodav.gads2020leaderboard.data.db.entities.HoursLeader;
import com.herodav.gads2020leaderboard.ui.LeaderListFragment;

import static com.herodav.gads2020leaderboard.utils.Status.SUCCESS;

public class HoursLeaderListFragment extends LeaderListFragment<HoursLeader> {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.leaders_list_fragment, container, false);
        setupUi(v);
        HoursLeaderViewModel mViewModel = ViewModelProviders.of(this).get(HoursLeaderViewModel.class);
        mViewModel.getHoursLeaders().observe(getViewLifecycleOwner(), resource -> {
            if (resource.status == SUCCESS) {
                setLeaders(resource.data);
            } else {
                setLeaders(null);
                Toast.makeText(getContext(), resource.message, Toast.LENGTH_LONG).show();
            }
            updateUI();
        });

        return v;
    }

}