package ru.alexandermalikov.testmvp.ui;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.alexandermalikov.testmvp.R;

public class PersonInfoFragment extends Fragment {

    public PersonInfoFragment() {
        // Required empty public constructor
    }

    public static PersonInfoFragment newInstance() {
        return new PersonInfoFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_person_info, container, false);
    }

}
