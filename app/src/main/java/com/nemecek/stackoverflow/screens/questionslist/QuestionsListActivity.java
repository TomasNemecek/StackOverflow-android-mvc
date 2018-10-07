package com.nemecek.stackoverflow.screens.questionslist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Toast;

import com.nemecek.stackoverflow.R;
import com.nemecek.stackoverflow.common.Constants;
import com.nemecek.stackoverflow.networking.QuestionSchema;
import com.nemecek.stackoverflow.networking.QuestionsListResponseSchema;
import com.nemecek.stackoverflow.networking.StackoverflowApi;
import com.nemecek.stackoverflow.questions.Question;
import com.nemecek.stackoverflow.screens.common.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class QuestionsListActivity extends BaseActivity implements QuestionsListViewMvcImpl.Listener {

    private StackoverflowApi mStackoverflowApi;

    private QuestionsListViewMvc mViewMvc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewMvc = new QuestionsListViewMvcImpl(LayoutInflater.from(this), null);
        mViewMvc.registerListener(this);
        setContentView(mViewMvc.getRootView());

        mStackoverflowApi = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(StackoverflowApi.class);

        setContentView(mViewMvc.getRootView());
    }

    @Override
    protected void onStart() {
        super.onStart();
        fetchQuestions();
    }

    private void fetchQuestions() {
        mStackoverflowApi.fetchLastActiveQuestions(Constants.QUESTIONS_LIST_PAGE_SIZE)
                .enqueue(new Callback<QuestionsListResponseSchema>() {
                    @Override
                    public void onResponse(Call<QuestionsListResponseSchema> call, Response<QuestionsListResponseSchema> response) {
                        if (response.isSuccessful()) {
                            bindQuestions(response.body().getQuestions());
                        } else {
                            networkCallFailed();
                        }
                    }

                    @Override
                    public void onFailure(Call<QuestionsListResponseSchema> call, Throwable t) {
                        networkCallFailed();
                    }
                } );
    }

    private void bindQuestions(List<QuestionSchema> questionSchemas) {
        List<Question> questions = new ArrayList<>(questionSchemas.size());
        for (QuestionSchema questionSchema : questionSchemas) {
            questions.add(new Question(questionSchema.getId(), questionSchema.getTitle()));
        }
        mViewMvc.bindQuestions(questions);
    }

    private void networkCallFailed() {
        Toast.makeText(this, R.string.error_network_call_failed, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onQuestionClicked(Question question) {
        Toast.makeText(this, question.getTitle(), Toast.LENGTH_SHORT).show();
    }
}
