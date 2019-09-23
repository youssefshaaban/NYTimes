package com.smartzone.technology.api;


import com.smartzone.technology.model.ResultReponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("/svc/mostpopular/v2/viewed/{period}.json")
    Single<ResultReponse> getArticles(@Path("period") int period, @Query("api-key") String apiKey);

}
