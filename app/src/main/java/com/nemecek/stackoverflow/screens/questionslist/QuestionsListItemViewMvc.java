package com.nemecek.stackoverflow.screens.questionslist;

import com.nemecek.stackoverflow.questions.Question;
import com.nemecek.stackoverflow.screens.common.ViewMvcInterface;

public interface QuestionsListItemViewMvc extends ViewMvcInterface{
    interface Listener {
        void onQuestionClicked(Question question);
    }

    void registerListener(Listener listener);
    void unregisterListener(Listener listener);
    void bindQuestion(Question question);
}
