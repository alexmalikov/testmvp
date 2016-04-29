package ru.alexandermalikov.testmvp.ui.views;


public interface AddPersonView {

    void showProgress(boolean show);

    void showMessage(String message);

    void finish();

}
