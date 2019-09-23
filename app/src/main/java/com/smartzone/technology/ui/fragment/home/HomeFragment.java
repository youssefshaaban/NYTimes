package com.smartzone.technology.ui.fragment.home;


import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smartzone.technology.R;
import com.smartzone.technology.databinding.FragmentHomeBinding;
import com.smartzone.technology.model.ArticleRepo;
import com.smartzone.technology.model.Result;
import com.smartzone.technology.ui.base.BaseFragment;
import com.smartzone.technology.ui.fragment.ArticlesAdapter;

import java.util.ArrayList;

import io.reactivex.disposables.CompositeDisposable;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment implements HomeView{


    FragmentHomeBinding fragmentHomeBinding;
    HomePresnterImp homePresnterImp;
    
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        fragmentHomeBinding= DataBindingUtil.inflate(inflater,R.layout.fragment_home, container, false);
        fragmentHomeBinding.contentRecycle.emptyRecycle.setLayoutManager(new LinearLayoutManager(getActivity()));
        fragmentHomeBinding.contentRecycle.tittleTxt.setText(getString(R.string.noData));
        fragmentHomeBinding.contentRecycle.emptyRecycle.setEmptyView(fragmentHomeBinding.contentRecycle.tittleTxt);
        homePresnterImp=new HomePresnterImp(new CompositeDisposable(),new ArticleRepo());
        homePresnterImp.onAttach(this);
        homePresnterImp.onPageStart();
        return fragmentHomeBinding.getRoot();
    }

    @Override
    public void setResult(ArrayList<Result> resultArticles) {
        fragmentHomeBinding.contentRecycle.emptyRecycle.setAdapter(new ArticlesAdapter(resultArticles,getActivity()));
    }
}
