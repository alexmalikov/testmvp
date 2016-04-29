package ru.alexandermalikov.testmvp.ui.presenters;


import android.content.Context;

import javax.inject.Inject;

import ru.alexandermalikov.testmvp.TestMvpApplication;
import ru.alexandermalikov.testmvp.ui.views.PersonInfoView;
import ru.alexandermalikov.testmvp.web.ApiClient;

public class PersonInfoPresenter {


    private static final String TAG = "TAGG : " + PersonListPresenter.class.getSimpleName();

    @Inject ApiClient mApiClient;

    private PersonInfoView mView;

    public PersonInfoPresenter(Context context, PersonInfoView view) {
        mView = view;
        ((TestMvpApplication) context.getApplicationContext()).getWebComponent().inject(this);
    }

    public void onDestroy() {
        mView = null;
    }


}
