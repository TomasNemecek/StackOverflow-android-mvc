package com.nemecek.stackoverflow.screens.common.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.FrameLayout;

import com.nemecek.stackoverflow.R;
import com.nemecek.stackoverflow.screens.common.controllers.BackPressDispatcher;
import com.nemecek.stackoverflow.screens.common.controllers.BackPressListener;
import com.nemecek.stackoverflow.screens.common.controllers.BaseActivity;
import com.nemecek.stackoverflow.screens.common.fragmentframehelper.FragmentFrameWrapper;
import com.nemecek.stackoverflow.screens.common.screensnavigator.ScreensNavigator;

import java.util.HashSet;
import java.util.Set;


public class MainActivity extends BaseActivity implements BackPressDispatcher, FragmentFrameWrapper {

    public static void startClearTop(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }

    private Set<BackPressListener> mBackPressListeners = new HashSet<>();
    private ScreensNavigator mScreensNavigator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_content_frame);
        mScreensNavigator = getCompositionRoot().getScreensNavigator();

        if (savedInstanceState == null) {
            mScreensNavigator.toQuestionsList();
        }
    }

    @Override
    public void onBackPressed() {
        boolean isBackPressedConsumedByListener = false;
        for (BackPressListener listener : mBackPressListeners) {
            if (listener.onBackPressed()) {
                isBackPressedConsumedByListener = true;
            }
        }
        if (!isBackPressedConsumedByListener) {
            super.onBackPressed();
        }
    }

    @Override
    public void registerListener(BackPressListener listener) {
        mBackPressListeners.add(listener);
    }

    @Override
    public void unregisterListener(BackPressListener listener) {
        mBackPressListeners.remove(listener);
    }

    @Override
    public FrameLayout getFragmentFrame() {
        return findViewById(R.id.frame_content);
    }
}
