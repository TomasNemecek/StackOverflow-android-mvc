package com.nemecek.stackoverflow.screens.questionslist.questionslistitem;

import com.nemecek.stackoverflow.questions.Question;
import com.nemecek.stackoverflow.screens.common.views.ObservableViewMvc;

public interface QuestionsListItemViewMvc extends ObservableViewMvc<QuestionsListItemViewMvc.Listener> {
    interface Listener {
        void onQuestionClicked(Question question);
    }
    void bindQuestion(Question question);
}
