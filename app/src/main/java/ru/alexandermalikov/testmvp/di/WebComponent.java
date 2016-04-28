package ru.alexandermalikov.testmvp.di;

import javax.inject.Singleton;

import dagger.Component;
import ru.alexandermalikov.testmvp.ui.AddPersonFragment;
import ru.alexandermalikov.testmvp.ui.PersonInfoFragment;
import ru.alexandermalikov.testmvp.ui.PersonListFragment;

@Singleton
@Component(modules={WebModule.class})
public interface WebComponent {

    void inject(PersonListFragment personListFragment);

    void inject(AddPersonFragment addPersonFragment);

    void inject(PersonInfoFragment personInfoFragment);

}
