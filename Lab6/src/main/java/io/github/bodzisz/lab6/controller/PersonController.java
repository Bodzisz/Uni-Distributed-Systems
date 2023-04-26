package io.github.bodzisz.lab6.controller;

import io.github.bodzisz.lab6.exception.FullListException;
import io.github.bodzisz.lab6.exception.PersonExistException;
import io.github.bodzisz.lab6.exception.PersonNotFoundException;
import io.github.bodzisz.lab6.model.Person;
import io.github.bodzisz.lab6.repository.PersonRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@RequestMapping("/persons")
public class PersonController {

    final PersonRepository personRepository;

    public PersonController(final PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping
    public ResponseEntity<CollectionModel<Person>> getAllPeople() throws PersonNotFoundException {
        final List<Person> personList = personRepository.getAllPersons();

        for (Person person : personList) {
            if(!person.hasLinks()) {
                person.add(
                        linkTo(methodOn(PersonController.class).getPerson(person.getId())).withSelfRel(),
                        linkTo(methodOn(PersonController.class).deletePerson(person.getId())).withRel("delete"),
                        linkTo(methodOn(PersonController.class).getAllPeople()).withRel("list all")
                );
            }
        }

        final Link link = linkTo(methodOn(PersonController.class).getAllPeople()).withSelfRel();
        return ResponseEntity.ok(CollectionModel.of(personList, link));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable final int id) throws PersonNotFoundException {
        final Person person = personRepository.getPerson(id);

        if(!person.hasLinks()) {
            person.add(
                    linkTo(methodOn(PersonController.class).getPerson(person.getId())).withSelfRel(),
                    linkTo(methodOn(PersonController.class).deletePerson(person.getId())).withRel("delete"),
                    linkTo(methodOn(PersonController.class).getAllPeople()).withRel("list all")
            );
        }

        return ResponseEntity.ok(person);
    }

    @PostMapping
    public ResponseEntity<Person> addPerson(@RequestBody final Person person) throws PersonExistException, FullListException, PersonNotFoundException {
        final Person personToReturn = personRepository.addPerson(person);

        if(!personToReturn.hasLinks()) {
            personToReturn.add(
                    linkTo(methodOn(PersonController.class).getPerson(personToReturn.getId())).withSelfRel(),
                    linkTo(methodOn(PersonController.class).deletePerson(personToReturn.getId())).withRel("delete"),
                    linkTo(methodOn(PersonController.class).getAllPeople()).withRel("list all")
            );
        }
        return ResponseEntity.ok(personToReturn);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Person> deletePerson(@PathVariable final int id) throws PersonNotFoundException {
        final Person personToReturn = personRepository.deletePerson(id);
        if(!personToReturn.hasLinks()) {
            personToReturn.add(
                    linkTo(methodOn(PersonController.class).getPerson(personToReturn.getId())).withSelfRel(),
                    linkTo(methodOn(PersonController.class).deletePerson(personToReturn.getId())).withRel("delete"),
                    linkTo(methodOn(PersonController.class).getAllPeople()).withRel("list all")
            );
        }

        return ResponseEntity.ok(personToReturn);
    }

    @PutMapping
    public ResponseEntity<Person> updatePerson(@RequestBody final Person person) throws PersonNotFoundException {
        final Person personToReturn = personRepository.updatePerson(person);
        if(!personToReturn.hasLinks()) {
            personToReturn.add(
                    linkTo(methodOn(PersonController.class).getPerson(personToReturn.getId())).withSelfRel(),
                    linkTo(methodOn(PersonController.class).deletePerson(personToReturn.getId())).withRel("delete"),
                    linkTo(methodOn(PersonController.class).getAllPeople()).withRel("list all")
            );
        }

        return ResponseEntity.ok(personToReturn);
    }
}
