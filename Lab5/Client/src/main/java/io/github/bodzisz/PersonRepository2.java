package io.github.bodzisz;

import java.util.List;

public interface PersonRepository2 {
    List<Person> getAllPersons();
    Person getPerson(int id) throws PersonNotFoundException_Exception;
    Person updatePerson(int id, String name, int age) throws PersonNotFoundException_Exception;
    boolean deletePerson(int id) throws PersonNotFoundException_Exception;
    Person addPerson(int id, String name, int age) throws PersonExistException_Exception;
    int countPersons();
}
