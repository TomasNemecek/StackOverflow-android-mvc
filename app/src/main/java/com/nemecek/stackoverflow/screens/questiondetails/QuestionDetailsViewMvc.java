package com.nemecek.stackoverflow.screens.questiondetails;

import com.nemecek.stackoverflow.questions.QuestionDetails;
import com.nemecek.stackoverflow.screens.common.navdrawer.DrawerItems;
import com.nemecek.stackoverflow.screens.common.navdrawer.NavDrawerViewMvc;
import com.nemecek.stackoverflow.screens.common.views.ObservableViewMvc;

public interface QuestionDetailsViewMvc extends ObservableViewMvc<QuestionDetailsViewMvc.Listener>, NavDrawerViewMvc {

    interface Listener {
        void onNavigateUpClicked();

        void onDrawerItemClicked(DrawerItems item);
    }

    void showProgressIndication();

    void hideProgressIndication();

    void bindQuestion(QuestionDetails questionDetails);
}
