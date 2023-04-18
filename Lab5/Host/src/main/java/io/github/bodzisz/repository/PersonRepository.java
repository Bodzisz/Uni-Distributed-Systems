package io.github.bodzisz.repository;

import io.github.bodzisz.exception.PersonExistException;
import io.github.bodzisz.exception.PersonNotFoundException;
import io.github.bodzisz.model.Person;

import java.util.List;

public interface PersonRepository {
    List<Person> getAllPersons();
    Person getPerson(int id) throws PersonNotFoundException;
    Person updatePerson(int id, String name, int age) throws PersonNotFoundException;
    boolean deletePerson(int id) throws PersonNotFoundException;
    Person addPerson(int id, String name, int age) throws PersonExistException;
    int countPersons();
}
