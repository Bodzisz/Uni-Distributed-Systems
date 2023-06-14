package io.github.bodzisz.lab6.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class PersonNotFoundException extends Exception{

    public PersonNotFoundException(final int id) {
        super("Person with id " + id + " not found");
    }
}
