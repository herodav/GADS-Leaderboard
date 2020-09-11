package com.herodav.gads2020leaderboard.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.Group;
import androidx.fragment.app.DialogFragment;

import com.herodav.gads2020leaderboard.R;

public class AppDialog extends DialogFragment {

    private static final String TAG = "AppDialog";

    public enum Type {SUCCESS, WARNING, ERROR}

    private ImageView imgImage;
    private TextView tvResultMessage;
    private Button btnAction;
    private ImageButton btnCancel;
    private Type mType;
    private String resultMessage, action;
    private Group alertGroup;
    private Group resultGroup;

    private final int ERROR = R.drawable.dial_ic_error;
    private final int SUCCESS = R.drawable.dial_ic_success;

    public static final String ARG_RESULT_MESSAGE = "message";
    public static final String ARG_ACTION = "action";
    public static final String ARG_TYPE = "type";

    public interface DialogEvents {
        void onActionClicked();
    }

    private DialogEvents mDialogEvents;

    @NonNull
    @Override
    public Dialog onCreateDialog(final Bundle args) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.app_dialog, null);

        imgImage = (ImageView) view.findViewById(R.id.dialog_icon);
        tvResultMessage = (TextView) view.findViewById(R.id.dialog_tv_result_message);
        btnAction = (Button) view.findViewById(R.id.dialog_btn_confirm);
        btnCancel = (ImageButton) view.findViewById(R.id.dialog_btn_cancel);

        alertGroup = (Group) view.findViewById(R.id.alert_group);
        resultGroup = (Group)view.findViewById(R.id.result_group);

        if (args != null) {

            mType = (Type) args.getSerializable(ARG_TYPE);
            resultMessage = args.getString(ARG_RESULT_MESSAGE);
            action = args.getString(ARG_ACTION);
        }

        setupLayout();

        builder.setView(view);
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        //Activities containing this fragment must implement its callbacks
        if (!(context instanceof DialogEvents)) {
            throw new ClassCastException(context.toString() + " must implement AppDialog.DialogEvents");
        }
        mDialogEvents = (DialogEvents) context;

        //Hide Success or Error Dialog after 5sec
        if (AppDialog.this.getType() == Type.SUCCESS || AppDialog.this.getType() == Type.ERROR) {
            new CountDownTimer(5000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                }

                @Override
                public void onFinish() {
                    AppDialog.this.dismiss();
                }
            }.start();
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mDialogEvents = null;
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
    }

    @Override
    public void onSaveInstanceState(Bundle args) {

        args.putSerializable(ARG_TYPE, mType);
        args.putString(ARG_RESULT_MESSAGE, resultMessage);
        args.putString(ARG_ACTION, action);

        super.onSaveInstanceState(args);
    }

    private void setupLayout() {
        tvResultMessage.setText(resultMessage);
        switch (this.mType) {

            case SUCCESS:
                alertGroup.setVisibility(View.GONE);
                imgImage.setImageDrawable(getResources().getDrawable(SUCCESS));
                tvResultMessage.setText(R.string.message_success);
                break;

            case WARNING:
                resultGroup.setVisibility(View.GONE);
                imgImage.setImageDrawable(getResources().getDrawable(ERROR));

                btnAction.setOnClickListener(v -> {
                    if (mDialogEvents != null) {
                        this.dismiss();
                        mDialogEvents.onActionClicked();
                    }
                });

                btnCancel.setOnClickListener(v -> AppDialog.super.onDismiss(getDialog()));
                break;

            case ERROR:
                alertGroup.setVisibility(View.GONE);
                imgImage.setImageDrawable(getResources().getDrawable(ERROR));
                tvResultMessage.setText(R.string.message_failure);
                break;
            default:
                throw new IllegalArgumentException(getActivity() +
                        " should set a dialog Type to determine the layout appearance");
        }


    }

    public void setAction(String action, View.OnClickListener listener) {
        this.action = action;
        if (this.btnAction != null) {
            this.btnAction.setOnClickListener(listener);
        }
    }


    public void setType(Type type) {
        mType = type;
    }

    public Type getType() {
        return this.mType;
    }

 /*   Button btnCancel, btnConfirm;
    ImageView imgAlertIcon;
    TextView tvMessage;
    User mUser;

    public AppDialog(User user) {
        this.mUser = user;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.app_dialog, null))
                // Add action buttons
                .setMessage(R.string.message_prompt_confirm);
        return builder.create();
    }*/

   /* @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_submit, container, false);
        setupUi(v);
        return v;
    }


    private void setupUi(View v) {
        imgAlertIcon = v.findViewById(R.id.submit_dialog_img);
        tvMessage = v.findViewById(R.id.submit_dialog_tv_message);
        btnCancel = v.findViewById(R.id.submit_dialog_btn_cancel);
        btnConfirm = v.findViewById(R.id.submit_dialog_btn_confirm);

        btnCancel.setOnClickListener((v1 ->{

            //todo: cancel
        }));

        btnConfirm.setOnClickListener((v2->{
            submitProject();
        }));

    }

    private void submitProject() {
        Toast.makeText(getContext(), mUser.getRepoUrl(), Toast.LENGTH_LONG).show();
    }*/
}
