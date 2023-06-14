package io.github.bodzisz.lab6.repository;

import io.github.bodzisz.lab6.exception.FullListException;
import io.github.bodzisz.lab6.exception.PersonExistException;
import io.github.bodzisz.lab6.exception.PersonNotFoundException;
import io.github.bodzisz.lab6.model.Person;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class PersonRepositoryImpl implements PersonRepository {

    private final Set<Person> personSet = new HashSet<>();
    private static final int MAX_SIZE = 4;

    private RabbitTemplate rabbitTemplate;

    public PersonRepositoryImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        Person person1 = new Person(1, "Kacper", 21);
        Person person2 = new Person(2, "Jurek", 35);

        this.personSet.add(person1);
        this.personSet.add(person2);
    }

    @Override
    public List<Person> getAllPersons() {
        rabbitTemplate.convertAndSend("personLogsTopic","allLogs", "Request for all people");
        return new ArrayList<>(personSet);
    }

    @Override
    public Person getPerson(int id) throws PersonNotFoundException {
        rabbitTemplate.convertAndSend("personLogsTopic","allLogs", "Request for person with id " + id);
        Optional<Person> personOptional = personSet.stream()
                .filter(person -> person.getId() == id)
                .findFirst();
        if(personOptional.isPresent()) {
            return personOptional.get();
        }
        else {
            rabbitTemplate.convertAndSend("personLogsTopic","allLogs", "Person with id " + id + " not found!");
            rabbitTemplate.convertAndSend("personLogsTopic","errorLogs", "Person with id " + id + " not found!");
            throw new PersonNotFoundException(id);
        }
    }

    @Override
    public Person updatePerson(final Person person) throws PersonNotFoundException{
        rabbitTemplate.convertAndSend("personLogsTopic","allLogs", "Request to update person with id " + person.getId());
        Person updatedPerson = getPerson(person.getId());
        updatedPerson.setName(person.getName());
        updatedPerson.setAge(person.getAge());
        return updatedPerson;
    }

    @Override
    public Person deletePerson(int id) throws PersonNotFoundException {
        rabbitTemplate.convertAndSend("personLogsTopic","allLogs", "Request to delete person with id " + id);
        Person person = getPerson(id);
        this.personSet.remove(person);
        return person;
    }

    @Override
    public Person addPerson(final Person person) throws PersonExistException, FullListException {
        rabbitTemplate.convertAndSend("personLogsTopic","allLogs",
                String.format("Request to add person: name(%s), age(%d)", person.getName(), person.getAge()));
        if(this.personSet.size() == MAX_SIZE) {
            rabbitTemplate.convertAndSend("personLogsTopic","allLogs",
                    String.format("List is full! Cannot add person: name(%s), age(%d)", person.getName(), person.getAge()));
            rabbitTemplate.convertAndSend("personLogsTopic","errorLogs",
                    String.format("List is full! Cannot add person: name(%s), age(%d)", person.getName(), person.getAge()));
            throw new FullListException();
        }
        if(this.personSet.stream().anyMatch(tmpPerson -> tmpPerson.getId() == person.getId())) {
            rabbitTemplate.convertAndSend("personLogsTopic","allLogs",
                    String.format("Cannot add person - already exists: id(%d), name(%s), age(%d)", person.getId(), person.getName(), person.getAge()));
            rabbitTemplate.convertAndSend("personLogsTopic","errorLogs",
                    String.format("Cannot add person - already exists: id(%d), name(%s), age(%d)", person.getId(), person.getName(), person.getAge()));
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
