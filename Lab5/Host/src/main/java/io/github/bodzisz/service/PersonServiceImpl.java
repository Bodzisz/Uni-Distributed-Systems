package io.github.bodzisz.service;

import io.github.bodzisz.exception.PersonExistException;
import io.github.bodzisz.exception.PersonNotFoundException;
import io.github.bodzisz.model.Person;
import io.github.bodzisz.repository.PersonRepository;
import io.github.bodzisz.repository.impl.PersonRepositoryImpl;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

import java.util.List;

@WebService(serviceName = "PersonService",
        endpointInterface = "io.github.bodzisz.service.PersonService")
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository = new PersonRepositoryImpl();

    @Override
    public Person getPerson(@WebParam(name = "id") int id) throws PersonNotFoundException {
        System.out.println("...called getPerson id="+id);
        return personRepository.getPerson(id);
    }

    @Override
    public Person addPerson(@WebParam(name = "id")int id, @WebParam(name = "name") String name,
                            @WebParam(name = "age") int age) throws PersonExistException {
        return personRepository.addPerson(id, name, age);
    }

    @Override
    public boolean deletePerson(@WebParam(name = "id") int id) throws PersonNotFoundException {
        return personRepository.deletePerson(id);
    }

    @Override
    public List<Person> getAllPersons() {
        System.out.println("...called getAllPersons");
        return personRepository.getAllPersons();
    }

    @Override
    public int countPersons() {
        return personRepository.countPersons();
    }
}
