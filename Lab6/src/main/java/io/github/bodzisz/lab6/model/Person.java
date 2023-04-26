package io.github.bodzisz.lab6.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

@Data
@AllArgsConstructor
public class Person extends RepresentationModel<Person> {

    private int id;
    private String name;
    private int age;
}
