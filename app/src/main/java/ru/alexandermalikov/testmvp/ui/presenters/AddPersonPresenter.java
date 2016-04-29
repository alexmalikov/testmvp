package ru.alexandermalikov.testmvp.ui.presenters;


import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.Random;

import javax.inject.Inject;

import ru.alexandermalikov.testmvp.TestMvpApplication;
import ru.alexandermalikov.testmvp.ui.views.AddPersonView;
import ru.alexandermalikov.testmvp.web.ApiClient;
import ru.alexandermalikov.testmvp.web.data.Person;
import ru.alexandermalikov.testmvp.web.data.StandardResponse;
import rx.Subscriber;

public class AddPersonPresenter {

    private static final String TAG = "TAGG : " + AddPersonPresenter.class.getSimpleName();

    @Inject ApiClient mApiClient;

    private AddPersonView mView;

    public AddPersonPresenter(Context context, AddPersonView view) {
        mView = view;
        ((TestMvpApplication) context.getApplicationContext()).getWebComponent().inject(this);
    }

    public void onDestroy() {
        mView = null;
    }


    public void addNewPerson(@NonNull String name, @NonNull String age, @NonNull String profession) {
        Person person = createPerson(name, age, profession);
        if (person == null) {
            mView.showMessage("Check entered data");
        } else {
            sendPerson(person);
        }
    }


    private Person createPerson(String name, String age, String profession) {
        if (name.length() == 0 || age.length() == 0 || profession.length() == 0) {
            Log.i(TAG, "Empty field");
            return null;
        }
        int ageNum;
        try {
            ageNum = Integer.parseInt(age);
        } catch (NumberFormatException e) {
            Log.i(TAG, "Incorrect age: " + e.getMessage());
            return null;
        }
        if (ageNum <= 0 || ageNum >150) {
            Log.i(TAG, "Incorrect age");
            return null;
        }
        int gender = new Random().nextInt(2);
        return new Person(name, ageNum, gender, profession);
    }


    private void sendPerson(Person person) {
        mView.showProgress(true);
        mApiClient.addPerson(person).subscribe(new Subscriber<StandardResponse>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                onUploadError(e);
            }

            @Override
            public void onNext(StandardResponse standardResponse) {
                onUploadFinished(standardResponse.isSuccess());
            }
        });
    }


    private void onUploadError(Throwable e) {
        Log.e(TAG, "onError(): " + e.getMessage());
        if (mView != null) {
            mView.showProgress(false);
            mView.showMessage("Error: " + e.getMessage());
        }
    }

    private void onUploadFinished(boolean success) {
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
