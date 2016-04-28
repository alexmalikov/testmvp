package ru.alexandermalikov.testmvp.ui;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.alexandermalikov.testmvp.R;


public class AddPersonFragment extends Fragment {


    public AddPersonFragment() {
        // Required empty public constructor
    }

    public static AddPersonFragment newInstance() {
        return new AddPersonFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_person, container, false);
    }

}
