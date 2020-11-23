package org.example.exception;

public class NotValidAmountForAuction extends RuntimeException {

    public NotValidAmountForAuction(
        final String msg) {
        super(msg);
    }
}
