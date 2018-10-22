package com.nemecek.stackoverflow.common.di;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;

import com.nemecek.stackoverflow.networking.StackoverflowApi;
import com.nemecek.stackoverflow.questions.FetchLastActiveQuestionsUseCase;
import com.nemecek.stackoverflow.questions.FetchQuestionDetailsUseCase;
import com.nemecek.stackoverflow.screens.common.MessagesDisplayer;
import com.nemecek.stackoverflow.screens.common.ScreensNavigator;
import com.nemecek.stackoverflow.screens.common.ViewMvcFactory;
import com.nemecek.stackoverflow.screens.questionslist.QuestionsListController;

public class ControllerCompositionRoot {

    private final CompositionRoot mCompositionRoot;
    private Activity mActivity;
    private QuestionsListController mQuestionsListController;

    public ControllerCompositionRoot(CompositionRoot compositionRoot, Activity activity) {
        this.mCompositionRoot = compositionRoot;
        this.mActivity = activity;
    }

    private StackoverflowApi getStackOverflowApi() {
        return mCompositionRoot.getStackOverflowApi();
    }

    private LayoutInflater getLayoutInflator() {
        return LayoutInflater.from(mActivity);
    }

    public ViewMvcFactory getViewMvcFactory() {
        return new ViewMvcFactory(getLayoutInflator());
    }

    public FetchQuestionDetailsUseCase getFetchQuestionDetailsUseCase() {
        return new FetchQuestionDetailsUseCase(getStackOverflowApi());
    }

    public FetchLastActiveQuestionsUseCase getFetchLastActiveQuestionsUseCase() {
        return new FetchLastActiveQuestionsUseCase(getStackOverflowApi());
    }

    public QuestionsListController getQuestionsListController() {
        return new QuestionsListController(getFetchLastActiveQuestionsUseCase(), getScreensNavigator(), getMessagesDisplayer());
    }

    private Context getContext() {
        return mActivity;
    }

    private MessagesDisplayer getMessagesDisplayer() {
        return new MessagesDisplayer(getContext());
    }

    private ScreensNavigator getScreensNavigator() {
        return new ScreensNavigator(getContext());
    }
}
