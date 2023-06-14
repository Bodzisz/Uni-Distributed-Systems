package io.github.bodzisz.lab7.exception;

public class ProductNotFoundException extends Exception {
    public ProductNotFoundException(final int id) {
        super("Product with id " + id + " not found");
    }
}
