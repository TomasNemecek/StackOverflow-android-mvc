package com.nemecek.stackoverflow.common.di.application;

import com.nemecek.stackoverflow.common.Constants;
import com.nemecek.stackoverflow.networking.StackoverflowApi;
import com.nemecek.stackoverflow.questions.FetchLastActiveQuestionsUseCase;
import com.nemecek.stackoverflow.questions.FetchQuestionDetailsUseCase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApplicationModule {

    @Singleton
    @Provides
    StackoverflowApi getStackOverflowApi(Retrofit retrofit) {
           return retrofit.create(StackoverflowApi.class);
    }

    @Singleton
    @Provides
    Retrofit getRetrofit() {
            return new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
    }

    @Provides
    FetchQuestionDetailsUseCase getFetchQuestionDetailsUseCase(StackoverflowApi stackoverflowApi) {
        return new FetchQuestionDetailsUseCase(stackoverflowApi);
    }

    @Provides
    FetchLastActiveQuestionsUseCase getFetchLastActiveQuestionsUseCase(StackoverflowApi stackoverflowApi) {
        return new FetchLastActiveQuestionsUseCase(stackoverflowApi);
    }
}
