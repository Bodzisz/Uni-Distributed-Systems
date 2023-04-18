package io.github.bodzisz.repository.impl;

import io.github.bodzisz.exception.PersonExistException;
import io.github.bodzisz.exception.PersonNotFoundException;
import io.github.bodzisz.model.Person;
import io.github.bodzisz.repository.PersonRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PersonRepositoryImpl implements PersonRepository {

    private Set<Person> personSet;

    public PersonRepositoryImpl() {
        this.personSet = new HashSet<>();
        this.personSet.add(new Person(1, "Kacper", 21));
        this.personSet.add(new Person(2, "Kubica", 42));
    }

    public List<Person> getAllPersons() {
        return new ArrayList<>(personSet);
    }

    public Person getPerson(int id) throws PersonNotFoundException {
        return personSet.stream()
                .filter(person -> person.getId() == id)
                .findFirst()
                .orElseThrow(PersonNotFoundException::new);
    }

    public Person updatePerson(int id, String name, int age) throws PersonNotFoundException {
        Person person = getPerson(id);
        person.setFirstName(name);
        person.setAge(age);
        return person;
    }

    public boolean deletePerson(int id) throws PersonNotFoundException {
        Person person = getPerson(id);
        this.personSet.remove(person);
        return true;
    }

    public Person addPerson(int id, String name, int age) throws PersonExistException {
        if(this.personSet.stream().anyMatch(person -> person.getId() == id)) {
            throw new PersonExistException();
        }

        Person person = new Person(id, name, age);
        this.personSet.add(person);
        return person;
    }



    public int countPersons() {
        return this.personSet.size();
    }
}
