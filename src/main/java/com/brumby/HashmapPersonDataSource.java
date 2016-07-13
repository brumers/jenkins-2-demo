package com.brumby;

import java.util.*;

/**
 * Created by jonathan on 15/06/2016.
 */
public class HashmapPersonDataSource implements PersonDataSource {

    private Map<String, List<Person>> peopleMap = new HashMap<>();

    @Override
    public List<Person> getPeople(String firstName, String surname) {
        return peopleMap.getOrDefault(firstName + "-" + surname, Collections.emptyList());
    }

    @Override
    public void putPerson(String firstName, String surname, int age) {
        peopleMap.getOrDefault(firstName + "-" + surname, new ArrayList<>())
                .add(new Person(firstName, surname, UUID.randomUUID(), age));
    }
}
