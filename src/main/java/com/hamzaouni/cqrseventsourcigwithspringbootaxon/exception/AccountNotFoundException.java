package com.hamzaouni.cqrseventsourcigwithspringbootaxon.exception;

import java.util.UUID;

/**
 * @author Hamza Ouni
 */
public class AccountNotFoundException extends Throwable {
    public AccountNotFoundException(UUID id) {
        super("Cannot found account number [" + id + "]");
    }
}
