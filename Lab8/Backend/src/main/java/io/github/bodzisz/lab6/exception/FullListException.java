package io.github.bodzisz.lab6.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class FullListException extends Exception {

    public FullListException() {
        super("List is full");
    }
}
