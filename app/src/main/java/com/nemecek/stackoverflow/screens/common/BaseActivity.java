package com.nemecek.stackoverflow.screens.common;

import android.support.v7.app.AppCompatActivity;

import com.nemecek.stackoverflow.App;
import com.nemecek.stackoverflow.common.di.ControllerCompositionRoot;

public class BaseActivity extends AppCompatActivity {

    private ControllerCompositionRoot mControllerCompositionRoot;

    protected ControllerCompositionRoot getCompositionRoot() {
        if(mControllerCompositionRoot == null) {
            mControllerCompositionRoot = new ControllerCompositionRoot(((App) getApplication()).getCompositionRoot(), this);
        }
        return mControllerCompositionRoot;
    }
}
