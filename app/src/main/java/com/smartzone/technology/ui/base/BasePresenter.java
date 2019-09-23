package com.smartzone.technology.ui.base;

import android.text.TextUtils;

import java.io.IOException;
import io.reactivex.disposables.CompositeDisposable;
import retrofit2.HttpException;

/**
 * Created by joe on 07/09/2018.
 */

public class BasePresenter<V extends MvpView> implements MvpPresenter<V> {
    private static final String TAG = "BasePresenter";

    private final CompositeDisposable mCompositeDisposable;

    private V mMvpView;

    public BasePresenter(
            CompositeDisposable compositeDisposable) {
        this.mCompositeDisposable = compositeDisposable;
    }

    @Override
    public void onAttach(V mvpView) {
        mMvpView = mvpView;
    }

    @Override
    public void onDetach() {
        mCompositeDisposable.dispose();
        mMvpView = null;
    }

    public boolean isViewAttached() {
        return mMvpView != null;
    }

    public V getMvpView() {
        return mMvpView;
    }

    public void checkViewAttached() {
        if (!isViewAttached()) throw new MvpViewNotAttachedException();
    }


    public CompositeDisposable getCompositeDisposable() {
        return mCompositeDisposable;
    }

    @Override
    public void handleApiError(Throwable error) {
//        if (error instanceof HttpException) {
////            if (((HttpException) error).code() == 500) {
////                getMvpView().sh;
////            } else if (((HttpException) error).code() == 404) {
////                getMvpView().showServerError();
////            } else if (((HttpException) error).code() == 401) {
////            } else {
////                getMvpView().showMessage(error.getMessage());
////            }
////        } else if (error instanceof IOException) {
////            getMvpView().showDialogErrorNetwork();
////        }
//        }
        getMvpView().hideLoading();
        getMvpView().showMessage(error.getMessage());
    }
    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call Presenter.onAttach(MvpView) before" +
                    " requesting data to the Presenter");
        }
    }

}
