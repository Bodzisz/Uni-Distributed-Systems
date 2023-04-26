package io.github.bodzisz.lab6.repository;

import io.github.bodzisz.lab6.exception.FullListException;
import io.github.bodzisz.lab6.exception.PersonExistException;
import io.github.bodzisz.lab6.exception.PersonNotFoundException;
import io.github.bodzisz.lab6.model.Person;

import java.util.List;

public interface PersonRepository {

    List<Person> getAllPersons();
    Person getPerson(int id) throws PersonNotFoundException;
    Person updatePerson(Person person) throws PersonNotFoundException;
    Person deletePerson(int id) throws PersonNotFoundException;
    Person addPerson(Person person) throws PersonExistException, FullListException;
    int countPersons();

}
