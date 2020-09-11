package com.herodav.gads2020leaderboard;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.herodav.gads2020leaderboard.ui.AppDialog;
import com.herodav.gads2020leaderboard.ui.projectSubmission.ProjectSubmissionFragment;

public class ProjectSubmissionActivity extends AppCompatActivity implements AppDialog.DialogEvents {

    private Fragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_submission);

        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);

        mFragment = ProjectSubmissionFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.submission_fragment_container, mFragment)
                .commit();
    }

    @Override
    public void onActionClicked() {
        //todo:
        Toast.makeText(ProjectSubmissionActivity.this, "Hiiii",
                Toast.LENGTH_LONG).show();
    }
}