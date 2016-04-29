package ru.alexandermalikov.testmvp.ui;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import ru.alexandermalikov.testmvp.R;
import ru.alexandermalikov.testmvp.ui.presenters.AddPersonPresenter;
import ru.alexandermalikov.testmvp.ui.views.AddPersonView;


public class AddPersonFragment extends Fragment implements AddPersonView {

    private EditText mEtName;
    private EditText mEtAge;
    private EditText mEtProfession;
    private ProgressBar mPbAddPerson;
    private Button mBtnAddPerson;

    private AddPersonPresenter mPresenter;

    public AddPersonFragment() {
        // Required empty public constructor
    }

    public static AddPersonFragment newInstance() {
        return new AddPersonFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        mPresenter = new AddPersonPresenter(getActivity(), this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_person, container, false);
        mEtName = (EditText) rootView.findViewById(R.id.et_name);
        mEtAge = (EditText) rootView.findViewById(R.id.et_age);
        mEtProfession = (EditText) rootView.findViewById(R.id.et_profession);
        mPbAddPerson = (ProgressBar) rootView.findViewById(R.id.pb_add_person);
        mBtnAddPerson = (Button) rootView.findViewById(R.id.btn_add);
        mBtnAddPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.addNewPerson(mEtName.getText().toString(),
                        mEtAge.getText().toString(), mEtProfession.getText().toString());
            }
        });
        return rootView;
    }


    @Override
    public void showProgress(boolean show) {
        mPbAddPerson.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
        mBtnAddPerson.setVisibility(show ? View.INVISIBLE : View.VISIBLE);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void finish() {
        getActivity().onBackPressed();
    }


    @Override
    public void onDestroy() {
        mPresenter.onDestroy();
        super.onDestroy();
    }
}
