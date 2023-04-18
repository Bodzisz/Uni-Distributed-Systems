package io.github.bodzisz;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PersonRepositoryImpl2 implements PersonRepository2 {
    private Set<Person> personSet;

    public PersonRepositoryImpl2() {
        this.personSet = new HashSet<>();
        this.personSet.add(new Person(1, "Kacper", 21));
        this.personSet.add(new Person(2, "Kubica", 42));
    }

    public List<Person> getAllPersons() {
        return new ArrayList<>(personSet);
    }

    public Person getPerson(int id) throws PersonNotFoundException_Exception {
        return personSet.stream()
                .filter(person -> person.getId() == id)
                .findFirst()
                .orElseThrow(PersonNotFoundException_Exception::new);
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
            throw new PersonExistException_Exception();
        }

        Person person = new Person(id, name, age);
        this.personSet.add(person);
        return person;
    }



    public int countPersons() {
        return this.personSet.size();
    }
}
