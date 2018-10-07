package com.nemecek.stackoverflow.screens.questiondetails;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.nemecek.stackoverflow.R;
import com.nemecek.stackoverflow.networking.QuestionDetailsResponseSchema;
import com.nemecek.stackoverflow.networking.QuestionSchema;
import com.nemecek.stackoverflow.networking.StackoverflowApi;
import com.nemecek.stackoverflow.questions.QuestionDetails;
import com.nemecek.stackoverflow.screens.common.BaseActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionDetailsActivity extends BaseActivity {

    public static final String EXTRA_QUESTION_ID = "EXTRA_QUESTION_ID";

    public static void start(Context context, String questionId) {
        Intent intent = new Intent(context, QuestionDetailsActivity.class);
        intent.putExtra(EXTRA_QUESTION_ID, questionId);
        context.startActivity(intent);
    }

    private StackoverflowApi mStackoverflowApi;
    private QuestionDetailsViewMvc mViewMvc;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mStackoverflowApi = getCompositionRoot().getStackOverflowApi();
        mViewMvc = getCompositionRoot().getViewMvcFactory().getQuestionDetailsViewMvc(null);
        setContentView(mViewMvc.getRootView());
    }

    @Override
    protected void onStart() {
        super.onStart();
        mViewMvc.showProgressIndication();
        fetchQuestionDetails();
    }

    private void fetchQuestionDetails() {
        mStackoverflowApi.fetchQuestionDetails(getQuestionId())
                .enqueue(new Callback<QuestionDetailsResponseSchema>() {
                    @Override
                    public void onResponse(Call<QuestionDetailsResponseSchema> call, Response<QuestionDetailsResponseSchema> response) {
                        if(response.isSuccessful()) {
                            bindQuestionDetails(response.body().getQuestion());
                        } else {
                            networkCallFailed();
                        }
                    }

                    @Override
                    public void onFailure(Call<QuestionDetailsResponseSchema> call, Throwable t) {
                        networkCallFailed();
                    }
                });
    }

    private String getQuestionId() {
        return getIntent().getStringExtra(EXTRA_QUESTION_ID);
    }

    private void bindQuestionDetails(QuestionSchema questionSchema) {
        mViewMvc.hideProgressIndication();
        mViewMvc.bindQuestion(new QuestionDetails(questionSchema.getId(), questionSchema.getTitle(), questionSchema.getBody()));

    }

    private void networkCallFailed() {
        mViewMvc.hideProgressIndication();
        Toast.makeText(this, R.string.error_network_call_failed, Toast.LENGTH_SHORT).show();
    }
}
