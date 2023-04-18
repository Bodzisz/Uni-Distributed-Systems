package io.github.bodzisz.exception;

import jakarta.xml.ws.WebFault;

@WebFault
public class PersonNotFoundException extends Exception{

    public PersonNotFoundException() {
        super("The specified person does not exist");
    }
}
