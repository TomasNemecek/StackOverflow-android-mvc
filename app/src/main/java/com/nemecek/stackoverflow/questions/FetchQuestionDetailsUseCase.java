package com.nemecek.stackoverflow.questions;

import com.nemecek.stackoverflow.common.BaseObservable;
import com.nemecek.stackoverflow.networking.QuestionDetailsResponseSchema;
import com.nemecek.stackoverflow.networking.QuestionSchema;
import com.nemecek.stackoverflow.networking.StackoverflowApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FetchQuestionDetailsUseCase extends BaseObservable<FetchQuestionDetailsUseCase.Listener> {

    private final StackoverflowApi mStackoverflowApi;

    public FetchQuestionDetailsUseCase(StackoverflowApi stackoverflowApi) {
        mStackoverflowApi = stackoverflowApi;
    }

    public interface Listener {

        void onQuestionDetailsFetched(QuestionDetails questionDetails);

        void onQuestionDetailsFetchFailed();
    }

    public void fetchQuestionDetailsAndNotify(String questionId) {
        mStackoverflowApi.fetchQuestionDetails(questionId)
                .enqueue(new Callback<QuestionDetailsResponseSchema>() {
                    @Override
                    public void onResponse(Call<QuestionDetailsResponseSchema> call, Response<QuestionDetailsResponseSchema> response) {
                        if(response.isSuccessful()) {
                            notifySuccess(response.body().getQuestion());
                        } else {
                            notifyFailure();
                        }
                    }

                    @Override
                    public void onFailure(Call<QuestionDetailsResponseSchema> call, Throwable t) {
                        notifyFailure();
                    }
                });
    }

    private void notifyFailure() {
        for(Listener listener : getListeners()) {
            listener.onQuestionDetailsFetchFailed();
        }
    }


    private void notifySuccess(QuestionSchema questionSchema) {
        for(Listener listener : getListeners()) {
            listener.onQuestionDetailsFetched(
                    new QuestionDetails(questionSchema.getId(), questionSchema.getTitle(), questionSchema.getBody())
            );
        }
    }
}
