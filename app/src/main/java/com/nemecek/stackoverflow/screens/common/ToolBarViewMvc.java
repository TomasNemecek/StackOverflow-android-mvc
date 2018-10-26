package com.nemecek.stackoverflow.screens.common;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.nemecek.stackoverflow.R;
import com.nemecek.stackoverflow.screens.common.views.BaseViewMvc;

public class ToolBarViewMvc extends BaseViewMvc {

    public interface NavigateUpClickListener {
        void onNavigateUpClicked();
    }

    private final TextView mTxtTitle;
    private final ImageButton mBtnBack;
    private NavigateUpClickListener mNavigateUpClickListener;

    public ToolBarViewMvc(LayoutInflater inflater, ViewGroup parent) {
        setRootView(inflater.inflate(R.layout.layout_toolbar, parent, false));
        mTxtTitle = findViewById(R.id.txt_toolbar_title);
        mBtnBack = findViewById(R.id.btn_back);
        mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mNavigateUpClickListener.onNavigateUpClicked();
            }
        });
    }
    public void setTitle(String title){
        mTxtTitle.setText(title);
    }


    public void enableUpButtonAndListen(NavigateUpClickListener navigateUpClickListener) {
        mNavigateUpClickListener = navigateUpClickListener;
        mBtnBack.setVisibility(View.VISIBLE);
    }
}
