package com.smartzone.technology.ui.fragment.home;

import com.smartzone.technology.EspressoIdlingResouce;
import com.smartzone.technology.app.GlameraApp;
import com.smartzone.technology.model.ArticleRepo;
import com.smartzone.technology.model.Result;
import com.smartzone.technology.model.ResultReponse;
import com.smartzone.technology.ui.base.BasePresenter;
import com.smartzone.technology.util.NetworkUtils;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class HomePresnterImp<V extends HomeView> extends BasePresenter<V> {
    ArticleRepo articleRepo;
    ArrayList<Result> resultsArticles;
    public HomePresnterImp(CompositeDisposable compositeDisposable, ArticleRepo articleRepo) {
        super(compositeDisposable);
        this.articleRepo = articleRepo;
    }

    public void onPageStart() {
        if (NetworkUtils.isConnected(GlameraApp.getAppContext())) {
            getMvpView().showLoading();
            getCompositeDisposable().add(
                    articleRepo.getAllArticles(7)
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeOn(Schedulers.io())
                            .subscribe(new Consumer<ResultReponse>() {
                                @Override
                                public void accept(ResultReponse resultReponse) throws Exception {
                                    if (resultReponse.getStatus()!=null&&resultReponse.getStatus().equalsIgnoreCase("OK")){
                                        resultsArticles=(ArrayList<Result>) resultReponse.getResults();
                                        getMvpView().hideLoading();
                                        getMvpView().setResult(resultsArticles);
                                        EspressoIdlingResouce.decrement();
                                    }
                                }
                            }, new Consumer<Throwable>() {
                                @Override
                                public void accept(Throwable throwable) throws Exception {
                                    handleApiError(throwable);
                                    EspressoIdlingResouce.decrement();
                                }
                            }));
        }
    }
}
