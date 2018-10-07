package com.nemecek.stackoverflow.screens.questionslist;

import com.nemecek.stackoverflow.questions.Question;
import com.nemecek.stackoverflow.screens.common.ObservableViewMvc;

import java.util.List;

interface QuestionsListViewMvc extends ObservableViewMvc<QuestionsListViewMvc.Listener> {
    interface Listener {
        void onQuestionClicked(Question question);
    }
    void bindQuestions(List<Question> questions);
}
