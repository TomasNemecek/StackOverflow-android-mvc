package com.nemecek.stackoverflow.screens.questionslist;

import com.nemecek.stackoverflow.questions.Question;
import com.nemecek.stackoverflow.screens.common.views.ObservableViewMvc;

import java.util.List;

public interface QuestionsListViewMvc extends ObservableViewMvc<QuestionsListViewMvc.Listener> {
    interface Listener {
        void onQuestionClicked(Question question);
    }

    void showProgressIndication();

    void hideProgressIndication();

    void bindQuestions(List<Question> questions);
}
