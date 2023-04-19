package io.github.bodzisz.service;

import io.github.bodzisz.exception.FullListException;
import io.github.bodzisz.exception.PersonExistException;
import io.github.bodzisz.exception.PersonNotFoundException;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import io.github.bodzisz.model.Person;

import java.util.List;

@WebService
public interface PersonService {

    @WebMethod
    Person getPerson(@WebParam(name = "id") int id) throws PersonNotFoundException;
    @WebMethod
    Person addPerson(@WebParam(name = "id") int id, @WebParam(name = "name") String name,
                     @WebParam(name = "age") int age) throws PersonExistException, FullListException;
    @WebMethod
    boolean deletePerson(@WebParam(name = "id") int id) throws PersonNotFoundException;
    @WebMethod
    List<Person> getAllPersons();
    @WebMethod
    int countPersons();

    @WebMethod
    boolean updatePerson(@WebParam(name = "id") int id,
                      @WebParam(name = "name") String name,
                      @WebParam(name = "age") int age) throws PersonNotFoundException;
}
