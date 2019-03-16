package za.ac.cput.factories;

import za.ac.cput.domain.Person;

public class PersonFactory {

    public static Person getClient(String idNo, String name, String surName)
    {
        Person myPerson = new Person.Builder()
                .idno(idNo)
                .name(name)
                .surname(surName)
                .build();

        return myPerson;

    }

}
