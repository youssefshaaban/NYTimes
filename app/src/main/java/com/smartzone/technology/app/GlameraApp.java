package com.smartzone.technology.app;

import android.app.Application;
import android.content.Context;


import com.smartzone.technology.api.ApiInterface;



/**
 * Created by joe on 07/09/2018.
 */

public class GlameraApp extends Application {
    private static GlameraApp instance;
    private ApiInterface apiInterface;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
        ApplicationComponent applicationComponent = DaggerApplicationComponent.builder().contextModule(new ContextModule(this)).build();
        apiInterface = applicationComponent.getApplicationService();

    }





    public static Context getAppContext() {
        return instance;
    }

    public static GlameraApp getAppInstance() {
        return instance;
    }

    public ApiInterface getApiInterface() {
        return apiInterface;
    }


}
