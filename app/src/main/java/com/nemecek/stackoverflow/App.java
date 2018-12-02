package com.nemecek.stackoverflow;

import android.app.Application;

import com.nemecek.stackoverflow.common.di.application.ApplicationComponent;
import com.nemecek.stackoverflow.common.di.application.ApplicationModule;
import com.nemecek.stackoverflow.common.di.application.DaggerApplicationComponent;

public class App extends Application {

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule())
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

}
