package com.herodav.gads2020leaderboard.ui.projectSubmission;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.textfield.TextInputEditText;
import com.herodav.gads2020leaderboard.R;
import com.herodav.gads2020leaderboard.model.User;
import com.herodav.gads2020leaderboard.ui.AppDialog;
import com.herodav.gads2020leaderboard.ui.AppDialog.Type;
import com.herodav.gads2020leaderboard.utils.InputValidatorHelper;

import static com.herodav.gads2020leaderboard.utils.Status.SUCCESS;

public class ProjectSubmissionFragment extends Fragment {

    TextInputEditText edtFirstName, edtLastName, edtEmail, edtProjectUrl;
    Button btnSubmit;
    String mFirstName, mLastName, mEmail, mRepoUrl;
    private ProjectSubmissionViewModel mViewModel;
    private AppDialog mDialog;
    ConstraintLayout form;
    ProgressBar progressBar;


    public static ProjectSubmissionFragment newInstance() {
        return new ProjectSubmissionFragment();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.project_submission_fragment, container, false);
        mViewModel = new ViewModelProvider(this).get(ProjectSubmissionViewModel.class);
        setupUi(v);
        return v;
    }

    private void setupUi(View v) {
        edtFirstName = (TextInputEditText) v.findViewById(R.id.edt_firstname);
        edtLastName = (TextInputEditText) v.findViewById(R.id.edt_lastname);
        edtEmail = (TextInputEditText) v.findViewById(R.id.edt_email);
        edtProjectUrl = (TextInputEditText) v.findViewById(R.id.edt_project_url);
        btnSubmit = (Button) v.findViewById(R.id.btn_submit);
        form = (ConstraintLayout) v.findViewById(R.id.form);
        progressBar = (ProgressBar) v.findViewById(R.id.submit_progressBar);
        btnSubmit.setOnClickListener((btn) -> {
            if (isValidForm()) {
                final User user = new User(mFirstName, mLastName, mEmail, mRepoUrl);

                mViewModel.setUser(user);
                displayDialog(Type.WARNING);
            }
        });
    }

    public void attemptSubmission(User user) {
        showProgress();
        if (mDialog.isVisible()) {
            mDialog.dismiss();
        }
        mViewModel.submit(user).observe(getViewLifecycleOwner(), resource -> {
            if (resource.status == SUCCESS) {
                hideProgress();
                displayDialog(Type.SUCCESS);
            } else {
                hideProgress();
                displayDialog(Type.ERROR);
            }
        });

    }

    private void showProgress() {
        form.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgress() {
        form.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
    }

    private void displayDialog(Type type) {
        mDialog = new AppDialog();
        mDialog.setType(type);
        mDialog.show(getParentFragmentManager(), "app_dialog");
    }

    private boolean isValidForm() {
        mFirstName = edtFirstName.getText().toString().trim();
        mLastName = edtLastName.getText().toString().trim();
        mEmail = edtEmail.getText().toString().trim();
        mRepoUrl = edtProjectUrl.getText().toString().trim();

        InputValidatorHelper helper = new InputValidatorHelper();

        //check fields with specific validation
        boolean isEmailValid = helper.isValidEmail(mEmail);
        boolean isGithubUrlValid = helper.isValidGithubUrl(mRepoUrl);

        return helper.isValidField(edtEmail, isEmailValid, getString(R.string.error_invalid_email)) &&
                helper.isValidField(edtProjectUrl, isGithubUrlValid, getString(R.string.error_github_link)) &&
                helper.isFilledForm(getString(R.string.error_required_field),
                        edtFirstName, edtLastName, edtEmail, edtProjectUrl);
    }

}