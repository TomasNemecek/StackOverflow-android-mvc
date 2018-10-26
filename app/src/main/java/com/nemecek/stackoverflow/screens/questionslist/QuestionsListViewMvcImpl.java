package com.nemecek.stackoverflow.screens.questionslist;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.nemecek.stackoverflow.R;
import com.nemecek.stackoverflow.questions.Question;
import com.nemecek.stackoverflow.screens.common.ToolBarViewMvc;
import com.nemecek.stackoverflow.screens.common.ViewMvcFactory;
import com.nemecek.stackoverflow.screens.common.navdrawer.BaseNavDrawerViewMvc;
import com.nemecek.stackoverflow.screens.common.navdrawer.DrawerItems;

import java.util.List;

public class QuestionsListViewMvcImpl extends BaseNavDrawerViewMvc<QuestionsListViewMvc.Listener> implements QuestionsRecyclerAdapter.Listener, QuestionsListViewMvc {

    private final ToolBarViewMvc mToolBarViewMvc;

    private final Toolbar mToolbar;
    private final ProgressBar mProgressBar;
    private final RecyclerView mRecyclerQuestions;
    private final QuestionsRecyclerAdapter mQuestionsListAdapter;

    public QuestionsListViewMvcImpl(LayoutInflater inflater, ViewGroup parent, ViewMvcFactory viewMvcFactory) {
        super(inflater, parent);

        setRootView(inflater.inflate(R.layout.layout_questions_list, parent, false));

        mProgressBar = findViewById(R.id.progress);

        mRecyclerQuestions = findViewById(R.id.rw_questions);
        mRecyclerQuestions.setLayoutManager(new LinearLayoutManager(getContext()));
        mQuestionsListAdapter = new QuestionsRecyclerAdapter(this, viewMvcFactory);
        mRecyclerQuestions.setAdapter(mQuestionsListAdapter);

        mToolbar = findViewById(R.id.toolbar);
        mToolBarViewMvc = viewMvcFactory.getToolBarViewMvc(mToolbar);
        initToolbar();
    }

    private void initToolbar() {
        mToolBarViewMvc.setTitle(getContext().getString(R.string.questions_list_screen_title));
        mToolbar.addView(mToolBarViewMvc.getRootView());
        mToolBarViewMvc.enableHamburgerButtonAndListen(new ToolBarViewMvc.HamburgerClickListener() {
            @Override
            public void onHamburgerClicked() {
                openDrawer();
            }
        });
    }

    @Override
    public void onQuestionClicked(Question question) {
        for(Listener listener : getListeners()) {
            listener.onQuestionClicked(question);
        }
    }

    @Override
    protected void onDrawerItemClicked(DrawerItems item) {
        for(Listener listener : getListeners()) {
            switch (item) {
                case QUESTIONS_LIST:
                    listener.onQuestionListClicked();
                    break;
            }
        }
    }

    @Override
    public void showProgressIndication() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressIndication() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void bindQuestions(List<Question> questions) {
        mQuestionsListAdapter.bindQuestions(questions);
    }

}
