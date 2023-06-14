package io.github.bodzisz.lab7.exception;

public class ProductNotInSaleException extends Exception {

    public ProductNotInSaleException(final String productName) {
        super(productName + " is not in sale");
    }
}
