package com.smartzone.technology.ui.base;


/**
 * Created by joe on 07/09/2018.
 */

public interface MvpPresenter<V extends MvpView> {
    void onAttach(V mvpView);

    void onDetach();

    void handleApiError(Throwable error);

}
