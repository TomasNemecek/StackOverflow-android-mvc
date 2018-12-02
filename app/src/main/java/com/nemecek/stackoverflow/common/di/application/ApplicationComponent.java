package com.nemecek.stackoverflow.common.di.application;

import com.nemecek.stackoverflow.questions.FetchLastActiveQuestionsUseCase;
import com.nemecek.stackoverflow.questions.FetchQuestionDetailsUseCase;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    public FetchLastActiveQuestionsUseCase getFetchLastActiveQuestionsUseCase();
    public FetchQuestionDetailsUseCase getFetchQuestionDetailsUseCase();
}
