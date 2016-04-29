package ru.alexandermalikov.testmvp.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import ru.alexandermalikov.testmvp.R;
import ru.alexandermalikov.testmvp.TestMvpApplication;
import ru.alexandermalikov.testmvp.web.ApiClient;
import ru.alexandermalikov.testmvp.web.data.Person;

public class PersonInfoFragment extends Fragment {

    @Inject ApiClient mApiClient;

    public PersonInfoFragment() {
        // Required empty public constructor
    }

    public static PersonInfoFragment newInstance(Person person) {
        return new PersonInfoFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((TestMvpApplication) getActivity().getApplication()).getWebComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_person_info, container, false);
    }

}
