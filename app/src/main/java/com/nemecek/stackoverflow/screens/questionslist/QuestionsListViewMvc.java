package com.nemecek.stackoverflow.screens.questionslist;

import com.nemecek.stackoverflow.questions.Question;
import com.nemecek.stackoverflow.screens.common.ViewMvcInterface;

import java.util.List;

interface QuestionsListViewMvc extends ViewMvcInterface {
    interface Listener {
        void onQuestionClicked(Question question);
    }

    void registerListener(Listener listener);

    void unregisterListener(Listener listener);

    void bindQuestions(List<Question> questions);
}
