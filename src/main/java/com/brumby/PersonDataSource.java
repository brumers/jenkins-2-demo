package com.brumby;

import java.util.List;

/**
 * Created by jonathan on 15/06/2016.
 */
public interface PersonDataSource {

    List<Person> getPeople(String firstName, String surname);

    void putPerson(String firstName, String surname, int age);
}
