package com.nemecek.stackoverflow.screens.questionslist;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.nemecek.stackoverflow.questions.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionsRecyclerAdapter extends RecyclerView.Adapter<QuestionsRecyclerAdapter.ViewHolder>
        implements QuestionsListItemViewMvc.Listener{

    public interface Listener {
        void onQuestionClicked(Question question);
    }

    //Due to Recycler being tightly coupled to view hodler - it's just a wrapper for mvc view
    static class ViewHolder extends RecyclerView.ViewHolder {
        private final QuestionsListItemViewMvc mViewMvc;

        ViewHolder(QuestionsListItemViewMvc viewMvc) {
            super(viewMvc.getRootView());
            this.mViewMvc = viewMvc;
        }
    }

    private final LayoutInflater mInflater;
    private final Listener mListener;

    private List<Question> mQuestions = new ArrayList<>();

    public QuestionsRecyclerAdapter(LayoutInflater inflater, Listener listener) {
        this.mInflater = inflater;
        this.mListener = listener;
    }

    public void bindQuestions(List<Question> questions) {
        mQuestions = new ArrayList<>(questions);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public QuestionsRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        QuestionsListItemViewMvc viewMvc = new QuestionsListItemViewMvcImpl(mInflater, viewGroup);
        viewMvc.registerListener(this);
        return new ViewHolder(viewMvc);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionsRecyclerAdapter.ViewHolder viewHolder, int i) {
        viewHolder.mViewMvc.bindQuestion(mQuestions.get(i));
    }

    @Override
    public int getItemCount() {
        return mQuestions.size();
    }

    @Override
    public void onQuestionClicked(Question question) {
        mListener.onQuestionClicked(question);
    }
}
