package ru.alexandermalikov.testmvp.ui;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import ru.alexandermalikov.testmvp.R;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "TAGG : " + MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Person List");
        addFragment(PersonListFragment.newInstance(), false);
    }


    private void addFragment(Fragment fragment, boolean addBackStack) {
        FragmentTransaction ft = getFragmentManager().beginTransaction().replace(R.id.container_main, fragment);
        if (addBackStack) {
            ft.addToBackStack(null);
        }
        ft.commit();
    }

    void showAddPerson() {
        addFragment(AddPersonFragment.newInstance(), true);
    }

    void showDeletePerson() {
        addFragment(PersonInfoFragment.newInstance(), true);
    }


    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}
