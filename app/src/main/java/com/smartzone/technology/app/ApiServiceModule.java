package com.smartzone.technology.app;

import com.smartzone.technology.api.ApiInterface;
import com.smartzone.technology.util.Constants;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = NetworkModule.class)
public class ApiServiceModule {
    @Provides
    @ApplicationScope
    public ApiInterface getApiService(Retrofit retrofit){
        return retrofit.create(ApiInterface.class);
    }


//    @Provides
//    public Gson gson(){
//        GsonBuilder gsonBuilder=new GsonBuilder();
//        gsonBuilder.registerTypeAdapter(DateTime.class,new DateTimeConverter());
//        return gsonBuilder.create();
//    }
    @Provides
    @ApplicationScope
    public Retrofit getRetrofit(OkHttpClient okhttpClient){
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.BASE_URL)
                .client(okhttpClient)
                .build();
    }


}
