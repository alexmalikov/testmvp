package ru.alexandermalikov.testmvp.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
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

    private ListView mLvPersons;
    private PersonAdapter mPersonAdapter;
    private FloatingActionButton mFabAddPerson;
    private ProgressBar mPbPersons;


    public PersonListFragment() {
        // Required empty public constructor
    }

    public static PersonListFragment newInstance() {
        return new PersonListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        ((TestMvpApplication) getActivity().getApplication()).getWebComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_person_list, container, false);
        mFabAddPerson = (FloatingActionButton) rootView.findViewById(R.id.fab);
        mFabAddPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getMainActivity().showAddPerson();
            }
        });
        mLvPersons = (ListView) rootView.findViewById(R.id.lv_persons);
        mPersonAdapter = new PersonAdapter(getActivity(), R.layout.list_person_item);
        mLvPersons.setAdapter(mPersonAdapter);
        mLvPersons.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        mPbPersons = (ProgressBar) rootView.findViewById(R.id.pb_persons);
        loadPersons();
        return rootView;
    }


    private MainActivity getMainActivity() {
        return (MainActivity) getActivity();
    }


    private void loadPersons() {
        showProgress(true);
        mApiClient.getPersonList().subscribe(new Subscriber<List<Person>>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted()");
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError(): " + e.getMessage());
                showProgress(false);
                Toast.makeText(getActivity(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNext(List<Person> personList) {
                showProgress(false);
                showLoadedPersons(personList);
            }
        });
    }


    private void showLoadedPersons(List<Person> personList) {
        mPersonAdapter.setPersonList(personList);
    }

    private void showProgress(boolean show) {
        mPbPersons.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
    }

}
