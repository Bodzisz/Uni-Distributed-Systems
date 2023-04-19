package io.github.bodzisz.service;

import io.github.bodzisz.exception.FullListException;
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
    private static final int MAX_SIZE = 4;

    @Override
    public Person getPerson(int id) throws PersonNotFoundException {
        System.out.println("...called getPerson id="+id);
        return personRepository.getPerson(id);
    }

    @Override
    public Person addPerson(int id, String name, int age) throws PersonExistException, FullListException {
        if(personRepository.countPersons() == MAX_SIZE) {
            throw new FullListException();
        }
        return personRepository.addPerson(id, name, age);
    }

    @Override
    public boolean deletePerson(int id) throws PersonNotFoundException {
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

    @Override
    public boolean updatePerson(int id, String name, int age) throws PersonNotFoundException {
        Person person = getPerson(id);
        person.setFirstName(name);
        person.setAge(age);
        return true;
    }
}
