package ru.alexandermalikov.testmvp.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import ru.alexandermalikov.testmvp.R;
import ru.alexandermalikov.testmvp.ui.presenters.PersonListPresenter;
import ru.alexandermalikov.testmvp.ui.views.PersonListView;
import ru.alexandermalikov.testmvp.web.data.Person;


public class PersonListFragment extends Fragment implements PersonListView {

    private static final String TAG = "TAGG : " + MainActivity.class.getSimpleName();

    private PersonListPresenter mPresenter;

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
        mPresenter = new PersonListPresenter(getActivity(), this);
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
                getMainActivity().showPersonInfo(mPersonAdapter.getPerson(position));
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
        mPresenter.loadPersons();
    }


    @Override
    public void showProgress(boolean show) {
        mPbPersons.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
    }

    @Override
    public void setPersonList(List<Person> personList) {
        mPersonAdapter.setPersonList(personList);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }


    @Override
    public void onDestroy() {
        mPresenter.onDestroy();
        super.onDestroy();
    }
}
