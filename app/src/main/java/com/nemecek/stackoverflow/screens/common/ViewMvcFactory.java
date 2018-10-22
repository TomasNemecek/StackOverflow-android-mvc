package com.nemecek.stackoverflow.screens.common;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.nemecek.stackoverflow.screens.questiondetails.QuestionDetailsViewMvc;
import com.nemecek.stackoverflow.screens.questiondetails.QuestionDetailsViewMvcImpl;
import com.nemecek.stackoverflow.screens.questionslist.QuestionsListViewMvc;
import com.nemecek.stackoverflow.screens.questionslist.QuestionsListViewMvcImpl;
import com.nemecek.stackoverflow.screens.questionslist.questionslistitem.QuestionsListItemViewMvc;
import com.nemecek.stackoverflow.screens.questionslist.questionslistitem.QuestionsListItemViewMvcImpl;


public class ViewMvcFactory {

    private final LayoutInflater mLayoutInflater;

    public ViewMvcFactory(LayoutInflater layoutInflater) {
        mLayoutInflater = layoutInflater;
    }

    public QuestionsListViewMvc getQuestionsListViewMvc(@Nullable ViewGroup parent) {
        return new QuestionsListViewMvcImpl(mLayoutInflater, parent, this);
    }

    public QuestionsListItemViewMvc getQuestionsListItemViewMvc(@Nullable ViewGroup parent) {
        return new QuestionsListItemViewMvcImpl(mLayoutInflater, parent);
    }

    public QuestionDetailsViewMvc getQuestionDetailsViewMvc(@Nullable ViewGroup parent) {
        return new QuestionDetailsViewMvcImpl(mLayoutInflater, parent);
    }
}
