package io.github.bodzisz.lab6.controller;

import io.github.bodzisz.lab6.exception.FullListException;
import io.github.bodzisz.lab6.exception.PersonExistException;
import io.github.bodzisz.lab6.exception.PersonNotFoundException;
import io.github.bodzisz.lab6.model.Person;
import io.github.bodzisz.lab6.repository.PersonRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/persons")
public class PersonController {

    final PersonRepository personRepository;

    public PersonController(final PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping
    public ResponseEntity<List<Person>> getAllPeople() {
        return ResponseEntity.ok(personRepository.getAllPersons().stream().sorted(Comparator.comparing(Person::getId)).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable final int id) throws PersonNotFoundException {
        final Person person = personRepository.getPerson(id);

        return ResponseEntity.ok(person);
    }

    @PostMapping
    public ResponseEntity<Person> addPerson(@RequestBody final Person person) throws PersonExistException, FullListException, PersonNotFoundException {
        final Person personToReturn = personRepository.addPerson(person);
        return ResponseEntity.ok(personToReturn);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Person> deletePerson(@PathVariable final int id) throws PersonNotFoundException {
        final Person personToReturn = personRepository.deletePerson(id);

        return ResponseEntity.ok(personToReturn);
    }

    @PutMapping
    public ResponseEntity<Person> updatePerson(@RequestBody final Person person) throws PersonNotFoundException {
        final Person personToReturn = personRepository.updatePerson(person);

        return ResponseEntity.ok(personToReturn);
    }
}
