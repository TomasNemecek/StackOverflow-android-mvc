package com.nemecek.stackoverflow.screens.questionslist;

import android.os.Bundle;
import android.widget.Toast;

import com.nemecek.stackoverflow.R;
import com.nemecek.stackoverflow.questions.FetchLastActiveQuestionsUseCase;
import com.nemecek.stackoverflow.questions.Question;
import com.nemecek.stackoverflow.screens.common.BaseActivity;
import com.nemecek.stackoverflow.screens.questiondetails.QuestionDetailsActivity;

import java.util.List;


public class QuestionsListActivity extends BaseActivity implements QuestionsListViewMvcImpl.Listener, FetchLastActiveQuestionsUseCase.Listener {

    private FetchLastActiveQuestionsUseCase mFetchLastActiveQuestionsUseCase;

    private QuestionsListViewMvc mViewMvc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewMvc = getCompositionRoot().getViewMvcFactory().getQuestionsListViewMvc(null);
        mViewMvc.registerListener(this);
        setContentView(mViewMvc.getRootView());

        mFetchLastActiveQuestionsUseCase = getCompositionRoot().getFetchLastActiveQuestionsUseCase();

        setContentView(mViewMvc.getRootView());
    }

    @Override
    protected void onStart() {
        super.onStart();
        mFetchLastActiveQuestionsUseCase.registerListener(this);

        mViewMvc.showProgressIndication();
        mFetchLastActiveQuestionsUseCase.fetchLastActiveQuestionsAndNotify();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mFetchLastActiveQuestionsUseCase.unregisterListener(this);
    }

    private void bindQuestions(List<Question> questions) {
        mViewMvc.bindQuestions(questions);
    }

    private void networkCallFailed() {
        Toast.makeText(this, R.string.error_network_call_failed, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onQuestionClicked(Question question) {
        QuestionDetailsActivity.start(this, question.getId());
    }

    @Override
    public void onLastActiveQuestionsFetched(List<Question> questions) {
        mViewMvc.hideProgressIndication();
        bindQuestions(questions);
    }

    @Override
    public void onLastActiveQuestionsFetchFailed() {
        mViewMvc.hideProgressIndication();
        networkCallFailed();
    }
}
