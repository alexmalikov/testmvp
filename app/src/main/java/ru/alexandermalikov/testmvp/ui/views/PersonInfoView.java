package ru.alexandermalikov.testmvp.ui.views;


public interface PersonInfoView {

    void showProgress(boolean show);

    void showMessage(String message);

    void finish();

}
