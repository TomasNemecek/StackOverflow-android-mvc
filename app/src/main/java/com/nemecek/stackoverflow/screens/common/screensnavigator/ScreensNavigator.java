package com.nemecek.stackoverflow.screens.common.screensnavigator;

import com.nemecek.stackoverflow.screens.common.fragmentframehelper.FragmentFrameHelper;
import com.nemecek.stackoverflow.screens.questiondetails.QuestionDetailsFragment;
import com.nemecek.stackoverflow.screens.questionslist.QuestionsListFragment;

public class ScreensNavigator {
    
    private final FragmentFrameHelper mFragmentFrameHelper;

    public ScreensNavigator(FragmentFrameHelper fragmentFrameHelper) {
        mFragmentFrameHelper = fragmentFrameHelper;
    }

    public void toQuestionDetails(String questionId) {
        mFragmentFrameHelper.replaceFragment(QuestionDetailsFragment.newInstance(questionId));
    }

    public void toQuestionsList() {
        mFragmentFrameHelper.replaceFragmentAndClearBackstack(QuestionsListFragment.newInstance());
    }

    public void navigateUp() {
        mFragmentFrameHelper.navigateUp();
    }
}
