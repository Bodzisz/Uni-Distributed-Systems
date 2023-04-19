package io.github.bodzisz;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PersonRepositoryImpl2 implements PersonRepository2 {
    private Set<Person> personSet;

    public PersonRepositoryImpl2() {
        this.personSet = new HashSet<>();
        Person person1 = new Person();
        person1.setAge(21);
        person1.setId(1);
        person1.setFirstName("Kacper");

        Person person2 = new Person();
        person2.setAge(21);
        person2.setId(1);
        person2.setFirstName("Kacper");
        this.personSet.add(person1);
        this.personSet.add(person2);
    }

    public List<Person> getAllPersons() {
        return new ArrayList<>(personSet);
    }

    public Person getPerson(int id) throws PersonNotFoundException_Exception {
        return personSet.stream()
                .filter(person -> person.getId() == id)
                .findFirst()
                .orElseThrow(() -> new PersonNotFoundException_Exception("Not found", new PersonNotFoundException()));
    }

    public Person updatePerson(int id, String name, int age) throws PersonNotFoundException_Exception {
        Person person = getPerson(id);
        person.setFirstName(name);
        person.setAge(age);
        return person;
    }

    public boolean deletePerson(int id) throws PersonNotFoundException_Exception {
        Person person = getPerson(id);
        this.personSet.remove(person);
        return true;
    }

    public Person addPerson(int id, String name, int age) throws PersonExistException_Exception {
        if(this.personSet.stream().anyMatch(person -> person.getId() == id)) {
            throw new PersonExistException_Exception("Person exists", new PersonExistException());
        }

        Person person = new Person();
        person.setAge(age);
        person.setId(id);
        person.setFirstName(name);
        this.personSet.add(person);
        return person;
    }

    @Override
    public boolean clearPersons() {
        this.personSet.clear();
        return true;
    }

    public int countPersons() {
        return this.personSet.size();
    }
}
