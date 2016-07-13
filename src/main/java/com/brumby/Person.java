package com.brumby;

import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.UUID;

/**
 * Created by jonathan on 13/06/2016.
 */
@Table(keyspace = "samplekeyspace", name = "people")
public class Person {

    @PartitionKey(value = 0)
    @Column(name = "firstname")
    private String name;

    @PartitionKey(value = 1)
    @Column(name = "surname")
    private String surname;

    @PartitionKey(value = 2)
    @Column(name = "id")
    private UUID id;

    @Column(name = "age")
    private int age;


    public Person() {
    }

    public Person(String name, String surname, UUID id, int age) {
        this.name = name;
        this.surname = surname;
        this.id = id;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Person person = (Person) o;

        return new EqualsBuilder()
                .append(age, person.age)
                .append(name, person.name)
                .append(surname, person.surname)
                .append(id, person.id)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(name)
                .append(surname)
                .append(id)
                .append(age)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("name", name)
                .append("surname", surname)
                .append("id", id)
                .append("age", age)
                .toString();
    }
}
