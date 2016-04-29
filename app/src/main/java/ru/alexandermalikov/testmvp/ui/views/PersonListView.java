package ru.alexandermalikov.testmvp.ui.views;


import java.util.List;

import ru.alexandermalikov.testmvp.web.data.Person;

public interface PersonListView {

    void showProgress(boolean show);

    void setPersonList(List<Person>personList);

    void showMessage(String message);

}
