package com.nemecek.stackoverflow.screens.questiondetails;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nemecek.stackoverflow.questions.FetchQuestionDetailsUseCase;
import com.nemecek.stackoverflow.questions.QuestionDetails;
import com.nemecek.stackoverflow.screens.common.controllers.BackPressDispatcher;
import com.nemecek.stackoverflow.screens.common.controllers.BackPressListener;
import com.nemecek.stackoverflow.screens.common.controllers.BaseFragment;
import com.nemecek.stackoverflow.screens.common.navdrawer.DrawerItems;
import com.nemecek.stackoverflow.screens.common.screensnavigator.ScreensNavigator;
import com.nemecek.stackoverflow.screens.common.toastshelper.ToastsHelper;

public class QuestionDetailsFragment extends BaseFragment implements FetchQuestionDetailsUseCase.Listener, QuestionDetailsViewMvc.Listener, BackPressListener{

    private static final String ARG_QUESTION_ID = "ARG_QUESTION_ID";

    public static QuestionDetailsFragment newInstance(String questionId) {
        Bundle args = new Bundle();
        args.putString(ARG_QUESTION_ID, questionId);
        
        QuestionDetailsFragment fragment = new QuestionDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private FetchQuestionDetailsUseCase mFetchQuestionDetailsUseCase;

    private ToastsHelper mToastsHelper;
    private ScreensNavigator mScreensNavigator;
    private BackPressDispatcher mBackPressDispatcher;

    private QuestionDetailsViewMvc mViewMvc;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFetchQuestionDetailsUseCase = getCompositionRoot().getFetchQuestionDetailsUseCase();
        mToastsHelper = getCompositionRoot().getToastHelper();
        mScreensNavigator = getCompositionRoot().getScreensNavigator();
        mBackPressDispatcher = getCompositionRoot().getBackPressDispatcher();
        mViewMvc = getCompositionRoot().getViewMvcFactory().getQuestionDetailsViewMvc(container);
        return mViewMvc.getRootView();
    }

    @Override
    public void onStart() {
        super.onStart();
        mFetchQuestionDetailsUseCase.registerListener(this);
        mViewMvc.registerListener(this);
        mBackPressDispatcher.registerListener(this);

        mViewMvc.showProgressIndication();

        mFetchQuestionDetailsUseCase.fetchQuestionDetailsAndNotify(getQuestionId());
    }

    @Override
    public void onStop() {
        super.onStop();
        mViewMvc.unregisterListener(this);
        mBackPressDispatcher.unregisterListener(this);
        mFetchQuestionDetailsUseCase.unregisterListener(this);
    }

    private String getQuestionId() {
        return getArguments().getString(ARG_QUESTION_ID);
    }

    private void bindQuestionDetails(QuestionDetails questionDetails) {
        mViewMvc.hideProgressIndication();
        mViewMvc.bindQuestion(questionDetails);
    }


    @Override
    public void onQuestionDetailsFetched(QuestionDetails questionDetails) {
        bindQuestionDetails(questionDetails);
    }

    @Override
    public void onQuestionDetailsFetchFailed() {
        mViewMvc.hideProgressIndication();
        mToastsHelper.showUseCaseError();
    }

    @Override
    public boolean onBackPressed() {
        if(mViewMvc.isDrawerOpen()) {
            mViewMvc.closeDrawer();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onNavigateUpClicked() {
        mScreensNavigator.navigateUp();
    }

    @Override
    public void onDrawerItemClicked(DrawerItems item) {
        switch (item) {
            case QUESTIONS_LIST:
                mScreensNavigator.toQuestionsList();
        }
    }
}
