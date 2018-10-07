package com.nemecek.stackoverflow.screens.questiondetails;

import com.nemecek.stackoverflow.questions.QuestionDetails;
import com.nemecek.stackoverflow.screens.common.ViewMvc;

public interface QuestionDetailsViewMvc extends ViewMvc {
    void showProgressIndication();

    void hideProgressIndication();

    void bindQuestion(QuestionDetails questionDetails);
}
