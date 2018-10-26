package com.nemecek.stackoverflow.screens.questiondetails;

import com.nemecek.stackoverflow.questions.QuestionDetails;
import com.nemecek.stackoverflow.screens.common.views.ObservableViewMvc;

public interface QuestionDetailsViewMvc extends ObservableViewMvc<QuestionDetailsViewMvc.Listener> {

    interface Listener {
        void onNavigateUpClicked();
    }

    void showProgressIndication();

    void hideProgressIndication();

    void bindQuestion(QuestionDetails questionDetails);
}
