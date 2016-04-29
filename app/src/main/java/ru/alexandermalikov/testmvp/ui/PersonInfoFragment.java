package ru.alexandermalikov.testmvp.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import ru.alexandermalikov.testmvp.R;
import ru.alexandermalikov.testmvp.ui.views.PersonInfoView;
import ru.alexandermalikov.testmvp.web.data.Person;

public class PersonInfoFragment extends Fragment implements PersonInfoView {

    private TextView mTvName;
    private TextView mTvAge;
    private TextView mTvGender;
    private TextView mTvProfession;
    private Button mBtnDelete;
    private ProgressBar mPbDelete;

    public PersonInfoFragment() {
        // Required empty public constructor
    }

    public static PersonInfoFragment newInstance(Person person) {
        return new PersonInfoFragment();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_person_info, container, false);
        mTvName = (TextView) rootView.findViewById(R.id.tv_name);
        mTvAge = (TextView) rootView.findViewById(R.id.tv_age);
        mTvGender = (TextView) rootView.findViewById(R.id.tv_gender);
        mTvProfession = (TextView) rootView.findViewById(R.id.tv_profession);
        mPbDelete = (ProgressBar) rootView.findViewById(R.id.pb_delete_person);
        mBtnDelete = (Button) rootView.findViewById(R.id.btn_delete);
        mBtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call delete in Presenter
            }
        });
        return rootView;
    }


    @Override
    public void showProgress(boolean show) {
        mPbDelete.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
        mBtnDelete.setVisibility(show ? View.INVISIBLE : View.VISIBLE);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }
}
