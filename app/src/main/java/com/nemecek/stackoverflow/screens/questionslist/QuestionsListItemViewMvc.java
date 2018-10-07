package com.nemecek.stackoverflow.screens.questionslist;

import com.nemecek.stackoverflow.questions.Question;
import com.nemecek.stackoverflow.screens.common.ObservableViewMvc;

public interface QuestionsListItemViewMvc extends ObservableViewMvc<QuestionsListItemViewMvc.Listener> {
    interface Listener {
        void onQuestionClicked(Question question);
    }
    void bindQuestion(Question question);
}
