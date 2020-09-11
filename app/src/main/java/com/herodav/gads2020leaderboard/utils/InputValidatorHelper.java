package com.herodav.gads2020leaderboard.utils;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidatorHelper {

    public boolean isValidEmail(String email) {
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean isValidGithubUrl(String url) {
        return url.startsWith("https://github.com/");
    }

    public boolean isNullOrEmpty(String string) {
        return TextUtils.isEmpty(string);
    }

    public boolean isValidField(EditText editText, boolean condition, String errorMessage){
        if (!condition){
            editText.requestFocus();
            editText.setError(errorMessage);
        }
        return condition;
    }

    public boolean isFilledForm(String errorMessage, EditText... editTexts) {

        boolean cancel = false;
        View focusView = null;

        for (EditText editText : editTexts) {
            String content = editText.getText().toString().trim();

            if (TextUtils.isEmpty(content)) {
                editText.setError(errorMessage);
                focusView = editText;
                cancel = true;
            }
        }
        if (cancel) {
            focusView.requestFocus();
            return false;
        }
        return true;
    }
}
