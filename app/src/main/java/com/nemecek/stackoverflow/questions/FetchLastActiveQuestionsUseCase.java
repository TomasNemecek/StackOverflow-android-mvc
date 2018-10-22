package com.nemecek.stackoverflow.questions;

import com.nemecek.stackoverflow.common.BaseObservable;
import com.nemecek.stackoverflow.common.Constants;
import com.nemecek.stackoverflow.networking.QuestionSchema;
import com.nemecek.stackoverflow.networking.QuestionsListResponseSchema;
import com.nemecek.stackoverflow.networking.StackoverflowApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FetchLastActiveQuestionsUseCase extends BaseObservable<FetchLastActiveQuestionsUseCase.Listener> {

    private final StackoverflowApi mStackoverflowApi;

    public FetchLastActiveQuestionsUseCase(StackoverflowApi stackoverflowApi) {
        mStackoverflowApi = stackoverflowApi;
    }

    public interface Listener {
        void onLastActiveQuestionsFetched(List<Question> questions);

        void onLastActiveQuestionsFetchFailed();
    }

    public void fetchLastActiveQuestionsAndNotify() {
        mStackoverflowApi.fetchLastActiveQuestions(Constants.QUESTIONS_LIST_PAGE_SIZE)
                .enqueue(new Callback<QuestionsListResponseSchema>() {
                    @Override
                    public void onResponse(Call<QuestionsListResponseSchema> call, Response<QuestionsListResponseSchema> response) {
                        if (response.isSuccessful()) {
                            notifySuccess(response.body().getQuestions());
                        } else {
                            notifyFailure();
                        }
                    }

                    @Override
                    public void onFailure(Call<QuestionsListResponseSchema> call, Throwable t) {
                       notifyFailure();
                    }
                } );
    }

    private void notifyFailure() {
        for(FetchLastActiveQuestionsUseCase.Listener listener : getListeners()) {
            listener.onLastActiveQuestionsFetchFailed();
        }
    }


    private void notifySuccess(List<QuestionSchema> questionSchemas) {
        List<Question> questions = new ArrayList<>(questionSchemas.size());
        for (QuestionSchema questionSchema : questionSchemas) {
            questions.add(new Question(questionSchema.getId(), questionSchema.getTitle()));
        }
        for (Listener listener : getListeners()) {
            listener.onLastActiveQuestionsFetched(questions);
        }
    }
}
