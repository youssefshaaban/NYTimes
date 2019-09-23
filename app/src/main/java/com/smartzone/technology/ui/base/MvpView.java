package com.smartzone.technology.ui.base;

import androidx.annotation.StringRes;


/**
 * Created by joe on 07/09/2018.
 */

public interface MvpView {
    void showLoading();
    void hideLoading();
    void onError(@StringRes int resId);
    void onError(String message);
    void showMessage(String message);
    void hideKeyboard();
}
