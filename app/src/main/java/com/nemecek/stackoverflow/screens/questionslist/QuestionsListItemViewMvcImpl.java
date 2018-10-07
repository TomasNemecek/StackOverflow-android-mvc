package com.nemecek.stackoverflow.screens.questionslist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nemecek.stackoverflow.R;
import com.nemecek.stackoverflow.questions.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionsListItemViewMvcImpl implements QuestionsListItemViewMvc {

    private final View mRootView;
    private final List<Listener> mListeners = new ArrayList(1);

    private Question mQuestion;
    private TextView mTxtTitle;

    public QuestionsListItemViewMvcImpl(LayoutInflater inflater, ViewGroup parent) {
        mRootView = inflater.inflate(R.layout.layout_question_list_item, parent, false);
        mTxtTitle= findViewById(R.id.txt_title);

        getRootView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(Listener listener : mListeners) {
                    listener.onQuestionClicked(mQuestion);
                }
            }
        });
    }

    private <T extends View> T findViewById(int id) {
        return getRootView().findViewById(id);
    }

    @Override
    public View getRootView() {
        return mRootView;
    }

    @Override
    public void registerListener(Listener listener) {
        mListeners.add(listener);
    }

    @Override
    public void unregisterListener(Listener listener) {
        mListeners.remove(listener);
    }

    @Override
    public void bindQuestion(Question question) {
        //Need to assign argument mQuestion
        mQuestion = question;

        //Set the title
        mTxtTitle.setText(question.getTitle());
    }
}
