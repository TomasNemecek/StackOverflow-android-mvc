package com.nemecek.stackoverflow.screens.common.controllers;

import android.support.v4.app.Fragment;

import com.nemecek.stackoverflow.App;
import com.nemecek.stackoverflow.common.di.ControllerCompositionRoot;

public class BaseFragment extends Fragment {

    private ControllerCompositionRoot mControllerCompositionRoot;

    protected ControllerCompositionRoot getCompositionRoot() {
        if(mControllerCompositionRoot == null) {
            mControllerCompositionRoot = new ControllerCompositionRoot(((App) requireActivity().getApplication()).getCompositionRoot(), requireActivity());
        }
        return mControllerCompositionRoot;
    }
}
