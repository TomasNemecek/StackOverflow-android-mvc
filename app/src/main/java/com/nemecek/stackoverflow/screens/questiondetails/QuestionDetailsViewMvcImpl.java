package com.nemecek.stackoverflow.screens.questiondetails;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nemecek.stackoverflow.R;
import com.nemecek.stackoverflow.questions.QuestionDetails;
import com.nemecek.stackoverflow.screens.common.BaseViewMvc;

public class QuestionDetailsViewMvcImpl extends BaseViewMvc implements QuestionDetailsViewMvc  {

    private ProgressBar mProgressBar;
    private TextView mTxtQuestionTitle;
    private TextView mTxtQuestionBody;

    public QuestionDetailsViewMvcImpl(LayoutInflater inflater, ViewGroup parent) {
        setRootView(inflater.inflate(R.layout.layout_question_detail, parent, false));
        mProgressBar = findViewById(R.id.progress);
        mTxtQuestionTitle = findViewById(R.id.txt_question_title);
        mTxtQuestionBody = findViewById(R.id.txt_question_body);
    }

    @Override
    public void showProgressIndication() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressIndication() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void bindQuestion(QuestionDetails questionDetails) {
        String questionTitle = questionDetails.getTitle();
        String questionBody = questionDetails.getBody();

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            mTxtQuestionTitle.setText(Html.fromHtml(questionTitle, Html.FROM_HTML_MODE_LEGACY));
            mTxtQuestionBody.setText(Html.fromHtml(questionBody, Html.FROM_HTML_MODE_LEGACY));
        } else {
            mTxtQuestionTitle.setText(Html.fromHtml(questionTitle));
            mTxtQuestionBody.setText(Html.fromHtml(questionBody));
        }
    }


}
