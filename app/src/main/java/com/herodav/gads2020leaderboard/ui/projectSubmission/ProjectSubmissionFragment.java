package com.herodav.gads2020leaderboard.ui.projectSubmission;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.textfield.TextInputEditText;
import com.herodav.gads2020leaderboard.R;
import com.herodav.gads2020leaderboard.model.User;
import com.herodav.gads2020leaderboard.ui.AppDialog;
import com.herodav.gads2020leaderboard.ui.AppDialog.Type;
import com.herodav.gads2020leaderboard.utils.InputValidatorHelper;

import static com.herodav.gads2020leaderboard.utils.Status.SUCCESS;

public class ProjectSubmissionFragment extends Fragment implements AppDialog.DialogEvents {

    TextInputEditText edtFirstName, edtLastName, edtEmail, edtProjectUrl;
    Button btnSubmit;
    String mFirstName, mLastName, mEmail, mRepoUrl;
    private ProjectSubmissionViewModel mViewModel;
    private AppDialog mDialog;

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
        btnSubmit.setOnClickListener((btn) -> {
            if (isValidForm()) {
                displayDialog(Type.WARNING);
            }
        });
    }

    private void attemptSubmission() {
        final User user = new User(mFirstName, mLastName, mEmail, mRepoUrl);
        mViewModel.submit(user).observe(getViewLifecycleOwner(), resource -> {
            if (resource.status == SUCCESS) {
                displayDialog(Type.SUCCESS);
            } else {
                displayDialog(Type.ERROR);
            }
        });

    }

    private void displayDialog(Type type) {
        mDialog = new AppDialog();
        mDialog.setType(type);
        mDialog.show(getParentFragmentManager(), "app_dialog");
    }

    private boolean isValidForm() {
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

    @Override
    public void onActionClicked() {
        attemptSubmission();
    }
}