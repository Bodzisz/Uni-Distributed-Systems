package io.github.bodzisz.lab6.repository;

import io.github.bodzisz.lab6.exception.FullListException;
import io.github.bodzisz.lab6.exception.PersonExistException;
import io.github.bodzisz.lab6.exception.PersonNotFoundException;
import io.github.bodzisz.lab6.model.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class PersonRepositoryImpl implements PersonRepository {

    private final Set<Person> personSet = new HashSet<>();
    private static final int MAX_SIZE = 4;

    public PersonRepositoryImpl() {
        Person person1 = new Person(1, "Kacper", 21);
        Person person2 = new Person(2, "Jurek", 35);

        this.personSet.add(person1);
        this.personSet.add(person2);
    }

    @Override
    public List<Person> getAllPersons() {
        return new ArrayList<>(personSet);
    }

    @Override
    public Person getPerson(int id) throws PersonNotFoundException {
        return personSet.stream()
                .filter(person -> person.getId() == id)
                .findFirst()
                .orElseThrow(() -> new PersonNotFoundException(id));
    }

    @Override
    public Person updatePerson(final Person person) throws PersonNotFoundException{
        Person updatedPerson = getPerson(person.getId());
        updatedPerson.setName(person.getName());
        updatedPerson.setAge(person.getAge());
        return updatedPerson;
    }

    @Override
    public Person deletePerson(int id) throws PersonNotFoundException {
        Person person = getPerson(id);
        this.personSet.remove(person);
        return person;
    }

    @Override
    public Person addPerson(final Person person) throws PersonExistException, FullListException {
        if(this.personSet.size() == MAX_SIZE) {
            throw new FullListException();
        }
        if(this.personSet.stream().anyMatch(tmpPerson -> tmpPerson.getId() == person.getId())) {
            throw new PersonExistException(person.getId());
        }

        this.personSet.add(person);
        return person;
    }

    @Override
    public int countPersons() {
        return this.personSet.size();
    }
}
