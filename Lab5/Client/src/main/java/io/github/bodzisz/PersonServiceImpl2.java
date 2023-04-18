package io.github.bodzisz;

import jakarta.jws.WebParam;
import jakarta.jws.WebService;

import java.util.List;

@WebService(serviceName = "PersonService",
        endpointInterface = "io.github.bodzisz.PersonService")
public class PersonServiceImpl2 implements PersonService {
    private final PersonRepository2 personRepository = new PersonRepositoryImpl2();

    @Override
    public Person getPerson(@WebParam(name = "id") int id) throws PersonNotFoundException_Exception {
        System.out.println("...called getPerson id="+id);
        return personRepository.getPerson(id);
    }

    @Override
    public Person addPerson(@WebParam(name = "id")int id, @WebParam(name = "name") String name,
                            @WebParam(name = "age") int age) throws PersonExistException_Exception {
        return personRepository.addPerson(id, name, age);
    }

    @Override
    public boolean deletePerson(@WebParam(name = "id") int id) throws PersonNotFoundException_Exception {
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
