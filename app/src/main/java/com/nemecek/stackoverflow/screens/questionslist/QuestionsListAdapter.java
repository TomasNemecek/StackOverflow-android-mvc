package com.nemecek.stackoverflow.screens.questionslist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.nemecek.stackoverflow.questions.Question;

public class QuestionsListAdapter extends ArrayAdapter<Question> implements QuestionsListItemViewMvc.Listener {

    private final OnQuestionClickListener mOnQuestionClickListener;

    public interface OnQuestionClickListener {
        void onQuestionClicked(Question question);
    }

    public QuestionsListAdapter(Context context,
                                OnQuestionClickListener onQuestionClickListener) {
        super(context, 0);
        mOnQuestionClickListener = onQuestionClickListener;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            QuestionsListItemViewMvc viewMvc = new QuestionsListItemViewMvcImpl(LayoutInflater.from(getContext()), parent);
            viewMvc.registerListener(this);
            convertView = viewMvc.getRootView();
            convertView.setTag(viewMvc);
        }

        final Question question = getItem(position);

        // bind the data to views
        QuestionsListItemViewMvc viewMvc = (QuestionsListItemViewMvc) convertView.getTag();
        viewMvc.bindQuestion(question);

        return convertView;
    }

    @Override
    public void onQuestionClicked(Question question) {
        mOnQuestionClickListener.onQuestionClicked(question);
    }
}
