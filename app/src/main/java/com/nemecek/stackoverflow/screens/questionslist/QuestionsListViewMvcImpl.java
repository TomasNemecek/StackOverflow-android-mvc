package com.nemecek.stackoverflow.screens.questionslist;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.nemecek.stackoverflow.R;
import com.nemecek.stackoverflow.questions.Question;
import com.nemecek.stackoverflow.screens.common.BaseObservableViewMvc;
import com.nemecek.stackoverflow.screens.common.ViewMvcFactory;

import java.util.List;

public class QuestionsListViewMvcImpl extends BaseObservableViewMvc<QuestionsListViewMvc.Listener> implements QuestionsRecyclerAdapter.Listener, QuestionsListViewMvc {
    private RecyclerView mRecyclerQuestions;
    private QuestionsRecyclerAdapter mQuestionsListAdapter;

    public QuestionsListViewMvcImpl(LayoutInflater inflater, ViewGroup parent, ViewMvcFactory viewMvcFactory) {
        setRootView(inflater.inflate(R.layout.layout_questions_list, parent, false));

        mRecyclerQuestions = findViewById(R.id.rw_questions);
        mRecyclerQuestions.setLayoutManager(new LinearLayoutManager(getContext()));
        mQuestionsListAdapter = new QuestionsRecyclerAdapter(this, viewMvcFactory);
        mRecyclerQuestions.setAdapter(mQuestionsListAdapter);
    }

    @Override
    public void onQuestionClicked(Question question) {
        for(Listener listener : getListeners()) {
            listener.onQuestionClicked(question);
        }
    }

    @Override
    public void bindQuestions(List<Question> questions) {
        mQuestionsListAdapter.bindQuestions(questions);
    }
}
