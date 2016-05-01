package ru.alexandermalikov.testmvp;


import android.app.Application;

import ru.alexandermalikov.testmvp.di.DaggerWebComponent;
import ru.alexandermalikov.testmvp.di.WebComponent;
import ru.alexandermalikov.testmvp.di.WebModule;


// TODO:
// 1. RxLifecycle / CompositeSubscription
// 2. Extract same logic to BaseFragment
// 3. Add gender selection
// 4. Inject Presenters with dagger??
public class TestMvpApplication extends Application {

    private WebComponent mWebComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mWebComponent = DaggerWebComponent.builder()
                .webModule(new WebModule())
                .build();
    }

    public WebComponent getWebComponent() {
        return mWebComponent;
    }
}
