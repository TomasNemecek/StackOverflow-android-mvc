package com.nemecek.stackoverflow.screens.common;

public interface ObservableViewMvc<ListenerType> extends ViewMvc {

    void registerListener(ListenerType listener);
    void unregisterListener(ListenerType listener);
}
