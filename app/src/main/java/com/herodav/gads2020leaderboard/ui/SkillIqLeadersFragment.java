package com.herodav.gads2020leaderboard.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.herodav.gads2020leaderboard.R;
import com.herodav.gads2020leaderboard.data.db.entities.SkillIqLeader;

import static com.herodav.gads2020leaderboard.utils.Status.SUCCESS;

public class SkillIqLeadersFragment extends LeadersListFragment<SkillIqLeader> {


    public static SkillIqLeadersFragment newInstance() {
        return new SkillIqLeadersFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.skill_iq_leaders_fragment, container, false);
        setupUi(v);
        SkillIqLeadersViewModel viewModel = new ViewModelProvider(this).get(SkillIqLeadersViewModel.class);
//        SkillIqLeadersViewModel viewModel = ViewModelProviders.of(this).get(SkillIqLeadersViewModel.class);
        viewModel.getSkillIqLeaders().observe(getViewLifecycleOwner(), resource -> {
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