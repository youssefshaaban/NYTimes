package com.smartzone.technology.app;


import com.smartzone.technology.api.ApiInterface;

import dagger.Component;

@ApplicationScope
@Component(modules = {ApiServiceModule.class})
public interface ApplicationComponent {
    ApiInterface getApplicationService();

}
