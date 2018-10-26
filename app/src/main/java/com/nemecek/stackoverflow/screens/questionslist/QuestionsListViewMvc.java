package com.nemecek.stackoverflow.screens.questionslist;

import com.nemecek.stackoverflow.questions.Question;
import com.nemecek.stackoverflow.screens.common.navdrawer.NavDrawerViewMvc;
import com.nemecek.stackoverflow.screens.common.views.ObservableViewMvc;

import java.util.List;

public interface QuestionsListViewMvc extends ObservableViewMvc<QuestionsListViewMvc.Listener>, NavDrawerViewMvc {

    interface Listener {
        void onQuestionClicked(Question question);

        void onQuestionListClicked();
    }

    void showProgressIndication();

    void hideProgressIndication();

    void bindQuestions(List<Question> questions);
}
