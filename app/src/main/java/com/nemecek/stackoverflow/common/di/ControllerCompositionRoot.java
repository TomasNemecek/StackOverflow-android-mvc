package com.nemecek.stackoverflow.common.di;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;

import com.nemecek.stackoverflow.common.di.application.ApplicationComponent;
import com.nemecek.stackoverflow.questions.FetchLastActiveQuestionsUseCase;
import com.nemecek.stackoverflow.questions.FetchQuestionDetailsUseCase;
import com.nemecek.stackoverflow.screens.common.ViewMvcFactory;
import com.nemecek.stackoverflow.screens.common.controllers.BackPressDispatcher;
import com.nemecek.stackoverflow.screens.common.fragmentframehelper.FragmentFrameHelper;
import com.nemecek.stackoverflow.screens.common.fragmentframehelper.FragmentFrameWrapper;
import com.nemecek.stackoverflow.screens.common.screensnavigator.ScreensNavigator;
import com.nemecek.stackoverflow.screens.common.toastshelper.ToastsHelper;
import com.nemecek.stackoverflow.screens.questionslist.QuestionsListController;

public class ControllerCompositionRoot {

    private final ApplicationComponent mApplicationComponent;
    private final FragmentActivity mActivity;

    public ControllerCompositionRoot(ApplicationComponent applicationComponent, FragmentActivity activity) {
        this.mApplicationComponent = applicationComponent;
        this.mActivity = activity;
    }

    public FragmentActivity getActivity() {
        return mActivity;
    }

    private Context getContext() {
        return mActivity;
    }

    private FragmentManager getFragmentManager() {
        return getActivity().getSupportFragmentManager();
    }

    private LayoutInflater getLayoutInflator() {
        return LayoutInflater.from(mActivity);
    }

    public ViewMvcFactory getViewMvcFactory() {
        return new ViewMvcFactory(getLayoutInflator());
    }

    public FetchQuestionDetailsUseCase getFetchQuestionDetailsUseCase() {
        return mApplicationComponent.getFetchQuestionDetailsUseCase();
    }

    public FetchLastActiveQuestionsUseCase getFetchLastActiveQuestionsUseCase() {
        return mApplicationComponent.getFetchLastActiveQuestionsUseCase();
    }

    public QuestionsListController getQuestionsListController() {
        return new QuestionsListController(getFetchLastActiveQuestionsUseCase(), getScreensNavigator(), getToastHelper(), getBackPressDispatcher());
    }

    public ToastsHelper getToastHelper() {
        return new ToastsHelper(getContext());
    }

    public ScreensNavigator getScreensNavigator() {
        return new ScreensNavigator(getFragmentFrameHelper());
    }

    public BackPressDispatcher getBackPressDispatcher() {
        return (BackPressDispatcher) getActivity();
    }

    public FragmentFrameWrapper getFragmentFrameWrapper() {
        return (FragmentFrameWrapper) getActivity();
    }

    public FragmentFrameHelper getFragmentFrameHelper() {
        return new FragmentFrameHelper(getActivity(), getFragmentFrameWrapper(), getFragmentManager());
    }
}
