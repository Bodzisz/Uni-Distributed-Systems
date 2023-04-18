package io.github.bodzisz.exception;

import jakarta.xml.ws.WebFault;

@WebFault
public class PersonExistException extends Exception {

    public PersonExistException() {
        super("This person already exists");
    }
}
