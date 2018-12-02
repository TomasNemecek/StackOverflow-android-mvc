package com.nemecek.stackoverflow.screens.common.controllers;

import android.support.v7.app.AppCompatActivity;

import com.nemecek.stackoverflow.App;
import com.nemecek.stackoverflow.common.di.ControllerCompositionRoot;
import com.nemecek.stackoverflow.common.di.application.ApplicationComponent;

public abstract class BaseActivity extends AppCompatActivity {

    private ControllerCompositionRoot mControllerCompositionRoot;

    protected ControllerCompositionRoot getCompositionRoot() {
        if(mControllerCompositionRoot == null) {
            mControllerCompositionRoot = new ControllerCompositionRoot(getApplicationComponent(), this);
        }
        return mControllerCompositionRoot;
    }

    private ApplicationComponent getApplicationComponent() {
        return ((App) getApplication()).getApplicationComponent();
    }
}
