package com.nemecek.stackoverflow.screens.questionslist.questionslistitem;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nemecek.stackoverflow.R;
import com.nemecek.stackoverflow.questions.Question;
import com.nemecek.stackoverflow.screens.common.views.BaseObservableViewMvc;

public class QuestionsListItemViewMvcImpl extends BaseObservableViewMvc<QuestionsListItemViewMvc.Listener> implements QuestionsListItemViewMvc {

    private Question mQuestion;
    private TextView mTxtTitle;

    public QuestionsListItemViewMvcImpl(LayoutInflater inflater, ViewGroup parent) {
       setRootView(inflater.inflate(R.layout.layout_question_list_item, parent, false));
        mTxtTitle= findViewById(R.id.txt_title);

        getRootView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(Listener listener : getListeners()) {
                    listener.onQuestionClicked(mQuestion);
                }
            }
        });
    }

    @Override
    public void bindQuestion(Question question) {
        //Need to assign argument mQuestion
        mQuestion = question;
        //Set the title
        mTxtTitle.setText(question.getTitle());
    }
}
