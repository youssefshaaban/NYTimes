package com.smartzone.technology.ui.fragment.home;

import com.smartzone.technology.model.Result;
import com.smartzone.technology.ui.base.MvpView;

import java.util.ArrayList;

public interface HomeView extends MvpView {
    void setResult(ArrayList<Result> rersultArticles);
}
