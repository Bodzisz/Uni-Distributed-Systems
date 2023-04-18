package io.github.bodzisz.service;

import io.github.bodzisz.exception.PersonExistException;
import io.github.bodzisz.exception.PersonNotFoundException;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import io.github.bodzisz.model.Person;

import java.util.List;

@WebService
public interface PersonService {

    @WebMethod
    Person getPerson(int id) throws PersonNotFoundException;
    @WebMethod
    Person addPerson(int id, String name, int age) throws PersonExistException;
    @WebMethod
    boolean deletePerson(int id) throws PersonNotFoundException;
    @WebMethod
    List<Person> getAllPersons();
    @WebMethod
    int countPersons();
}
