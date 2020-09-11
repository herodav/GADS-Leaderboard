package com.herodav.gads2020leaderboard;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import com.herodav.gads2020leaderboard.ui.AppDialog;
import com.herodav.gads2020leaderboard.ui.projectSubmission.ProjectSubmissionFragment;
import com.herodav.gads2020leaderboard.ui.projectSubmission.ProjectSubmissionViewModel;

public class ProjectSubmissionActivity extends AppCompatActivity implements AppDialog.DialogEvents {

    private ProjectSubmissionFragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_submission);
        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mFragment = ProjectSubmissionFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.submission_fragment_container, mFragment)
                .commit();
    }

    @Override
    public void onActionClicked() {
        ProjectSubmissionViewModel viewModel = new ViewModelProvider(mFragment).get(ProjectSubmissionViewModel.class);
        viewModel.getUser().observe(mFragment.getViewLifecycleOwner(), (user) -> {
            String name = user.getFirstName();
            mFragment.attemptSubmission(user);
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}