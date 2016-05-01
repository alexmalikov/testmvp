package ru.alexandermalikov.testmvp.ui.presenters;


import android.content.Context;
import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import ru.alexandermalikov.testmvp.TestMvpApplication;
import ru.alexandermalikov.testmvp.ui.views.PersonListView;
import ru.alexandermalikov.testmvp.web.ApiClient;
import ru.alexandermalikov.testmvp.web.data.Person;
import rx.Subscriber;


public class PersonListPresenter {

    private static final String TAG = "TAGG : " + PersonListPresenter.class.getSimpleName();

    @Inject ApiClient mApiClient;

    private PersonListView mView;


    public PersonListPresenter(Context context, PersonListView view) {
        mView = view;
        ((TestMvpApplication) context.getApplicationContext()).getWebComponent().inject(this);
    }

    public void onDestroy() {
        mView = null;
    }

    public void loadPersons() {
        mView.showProgress(true);
        mApiClient.getPersonList().subscribe(new Subscriber<List<Person>>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted()");
            }

            @Override
            public void onError(Throwable e) {
                onLoadError(e);
            }

            @Override
            public void onNext(List<Person> personList) {
                onLoadFinished(personList);
            }
        });
    }


    private void onLoadError(Throwable e) {
        Log.e(TAG, "onError(): " + e.getMessage());
        if (mView != null) {
            mView.showProgress(false);
            mView.showMessage("Error: " + e.getMessage());
        }
    }

    private void onLoadFinished(List<Person> personList) {
        if (mView != null) {
            mView.showProgress(false);
            mView.setPersonList(personList);
        }
    }

}
