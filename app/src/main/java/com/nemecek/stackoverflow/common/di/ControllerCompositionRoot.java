package com.nemecek.stackoverflow.common.di;

import android.app.Activity;
import android.view.LayoutInflater;

import com.nemecek.stackoverflow.networking.StackoverflowApi;
import com.nemecek.stackoverflow.questions.FetchQuestionDetailsUseCase;
import com.nemecek.stackoverflow.screens.common.ViewMvcFactory;

public class ControllerCompositionRoot {

    private final CompositionRoot mCompositionRoot;
    private Activity mActivity;

    public ControllerCompositionRoot(CompositionRoot compositionRoot, Activity activity) {
        this.mCompositionRoot = compositionRoot;
        this.mActivity = activity;
    }

    public StackoverflowApi getStackOverflowApi() {
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
}
