package ru.alexandermalikov.testmvp.ui.presenters;


import android.content.Context;
import android.util.Log;

import javax.inject.Inject;

import ru.alexandermalikov.testmvp.TestMvpApplication;
import ru.alexandermalikov.testmvp.ui.views.PersonInfoView;
import ru.alexandermalikov.testmvp.web.ApiClient;
import ru.alexandermalikov.testmvp.web.data.Person;
import ru.alexandermalikov.testmvp.web.data.StandardResponse;
import rx.Subscriber;

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


    public void deletePerson(Person person) {
        mView.showProgress(true);
        mApiClient.deletePerson(person.getId()).subscribe(new Subscriber<StandardResponse>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                onDeleteError(e);
            }

            @Override
            public void onNext(StandardResponse standardResponse) {
                onDeleteFinished(standardResponse.isSuccess());
            }
        });
    }


    private void onDeleteError(Throwable e) {
        Log.e(TAG, "onError(): " + e.getMessage());
        if (mView != null) {
            mView.showProgress(false);
            mView.showMessage("Error: " + e.getMessage());
        }
    }


    private void onDeleteFinished(boolean success) {
        if (mView == null) {
            return;
        }
        mView.showProgress(false);
        if (success) {
            mView.finish();
        } else {
            mView.showMessage("Some error has occurred");
        }
    }


}
