package io.github.bodzisz.lab6.controller;

import io.github.bodzisz.lab6.exception.FullListException;
import io.github.bodzisz.lab6.exception.PersonExistException;
import io.github.bodzisz.lab6.exception.PersonNotFoundException;
import io.github.bodzisz.lab6.model.Person;
import io.github.bodzisz.lab6.repository.PersonRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@RequestMapping("/persons")
public class PersonController {

    final PersonRepository personRepository;

    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping
    final CollectionModel<EntityModel<Person>> getAllPeople() throws PersonNotFoundException {
        final List<Person> personList = personRepository.getAllPersons();

        final List<EntityModel<Person>> modelList = new ArrayList<>();
        for (Person person : personList) {
            EntityModel<Person> of = EntityModel.of(person,
                    linkTo(methodOn(PersonController.class).getPerson(person.getId())).withSelfRel(),
                    linkTo(methodOn(PersonController.class).deletePerson(person.getId())).withRel("delete"),
                    linkTo(methodOn(PersonController.class).getAllPeople()).withRel("list all")
            );
            modelList.add(of);
        }

        return CollectionModel.of(modelList, linkTo(methodOn(PersonController.class).getAllPeople()).withSelfRel());
    }

    @GetMapping("/{id}")
    final ResponseEntity<Person> getPerson(@PathVariable final int id) throws PersonNotFoundException {
        return ResponseEntity.ok(personRepository.getPerson(id));
    }

    @PostMapping
    final ResponseEntity<Person> addPerson(@RequestBody final Person person) throws PersonExistException, FullListException {
        final Person personToReturn = personRepository.addPerson(person);
        return ResponseEntity.ok(personToReturn);
    }

    @DeleteMapping("/{id}")
    final ResponseEntity<Person> deletePerson(@PathVariable final int id) throws PersonNotFoundException {
        final Person personToReturn = personRepository.deletePerson(id);
        return ResponseEntity.ok(personToReturn);
    }

    @PutMapping
    final ResponseEntity<Person> updatePerson(@RequestBody final Person person) throws PersonNotFoundException {
        return ResponseEntity.ok(personRepository.updatePerson(person));
    }
}
