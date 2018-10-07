package com.nemecek.stackoverflow.screens.questionslist;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nemecek.stackoverflow.R;
import com.nemecek.stackoverflow.questions.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionsListViewMvcImpl implements QuestionsRecyclerAdapter.Listener, QuestionsListViewMvc {

    private final View mRootView;

    private RecyclerView mRecyclerQuestions;
    private QuestionsRecyclerAdapter mQuestionsListAdapter;

    private final List<Listener> mListeners = new ArrayList<>(1);

    public QuestionsListViewMvcImpl(LayoutInflater inflater, ViewGroup parent) {
        mRootView = inflater.inflate(R.layout.layout_questions_list, parent, false);

        mRecyclerQuestions = findViewById(R.id.rw_questions);
        mRecyclerQuestions.setLayoutManager(new LinearLayoutManager(getContext()));
        mQuestionsListAdapter = new QuestionsRecyclerAdapter(inflater, this);
        mRecyclerQuestions.setAdapter(mQuestionsListAdapter);
    }

    @Override
    public void registerListener(Listener listener) {
        mListeners.add(listener);
    }

    @Override
    public void unregisterListener(Listener listener) {
        mListeners.remove(listener);
    }

    private Context getContext() {
        return getRootView().getContext();
    }

    private <T extends View> T findViewById(int id) {
        return getRootView().findViewById(id);
    }

    @Override
    public View getRootView() {
        return mRootView;
    }


    @Override
    public void onQuestionClicked(Question question) {
        for(Listener listener : mListeners) {
            listener.onQuestionClicked(question);
        }
    }

    @Override
    public void bindQuestions(List<Question> questions) {
        mQuestionsListAdapter.bindQuestions(questions);
    }
}
