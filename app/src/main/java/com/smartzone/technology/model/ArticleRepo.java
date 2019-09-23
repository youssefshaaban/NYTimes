package com.smartzone.technology.model;

import com.smartzone.technology.app.GlameraApp;
import com.smartzone.technology.util.Constants;

import io.reactivex.Observable;

public class ArticleRepo {

    public Observable<ResultReponse> getAllArticles(int period){
        return GlameraApp.getAppInstance().getApiInterface().getArticles(period, Constants.API_KEY).toObservable();
    }
}
