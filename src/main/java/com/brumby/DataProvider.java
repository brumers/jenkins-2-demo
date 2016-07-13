package com.brumby;

import java.util.UUID;

/**
 * Created by jonathan on 15/06/2016.
 */
public class DataProvider {

    public static Person getPerson(String firstName, String surname){
        return new Person(firstName, surname, UUID.randomUUID(), 25);
    }



}
