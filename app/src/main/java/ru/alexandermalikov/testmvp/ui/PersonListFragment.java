package ru.alexandermalikov.testmvp.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import ru.alexandermalikov.testmvp.R;
import ru.alexandermalikov.testmvp.TestMvpApplication;
import ru.alexandermalikov.testmvp.web.ApiClient;
import ru.alexandermalikov.testmvp.web.data.Person;
import rx.Subscriber;


public class PersonListFragment extends Fragment {

    private static final String TAG = "TAGG : " + MainActivity.class.getSimpleName();

    @Inject ApiClient mApiClient;


    public PersonListFragment() {
        // Required empty public constructor
    }

    public static PersonListFragment newInstance() {
        return new PersonListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((TestMvpApplication) getActivity().getApplication()).getWebComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_person_list, container, false);
        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getMainActivity().showAddPerson();
            }
        });
        loadPersons();
        return rootView;
    }


    private MainActivity getMainActivity() {
        return (MainActivity) getActivity();
    }


    private void loadPersons() {
        mApiClient.getPersonList().subscribe(new Subscriber<List<Person>>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted()");
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError(): " + e.getMessage());
                Toast.makeText(getActivity(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNext(List<Person> persons) {
                Toast.makeText(getActivity(), "Persons: " + persons.size(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
