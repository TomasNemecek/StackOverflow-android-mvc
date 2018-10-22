package com.nemecek.stackoverflow.screens.common;

import android.content.Context;

import com.nemecek.stackoverflow.screens.questiondetails.QuestionDetailsActivity;

public class ScreensNavigator {

    private final Context mContext;

    public ScreensNavigator(Context context) {
        mContext = context;
    }

    public void toDialogDetails(String questionId) {
        QuestionDetailsActivity.start(mContext, questionId);
    }
}
