package io.github.bodzisz.lab7.exception;

public class NegativeValueException extends Exception {

    public NegativeValueException(final String field) {
        super("Field " + field + " cannot have negative value");
    }
}
