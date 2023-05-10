package io.github.bodzisz.lab7.exception;

public class ExceededSaleException extends Exception {

    public ExceededSaleException(final int actualQuantity, final int sellQuantity, final String productName) {
        super("Cannot sell " + sellQuantity + " " + productName + ". Only " + actualQuantity + " available");
    }
}
