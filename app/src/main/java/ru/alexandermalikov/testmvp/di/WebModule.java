package ru.alexandermalikov.testmvp.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.alexandermalikov.testmvp.web.ApiClient;

@Module
public class WebModule {

    public WebModule() {
    }

    @Provides
    @Singleton
    ApiClient provideApiClient() {
        return new ApiClient();
    }

}
