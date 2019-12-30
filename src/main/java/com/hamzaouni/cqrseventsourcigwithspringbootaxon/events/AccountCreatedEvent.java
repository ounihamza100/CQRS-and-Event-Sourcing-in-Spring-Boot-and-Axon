package com.hamzaouni.cqrseventsourcigwithspringbootaxon.events;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * @author Hamza Ouni
 */
@Data
public class AccountCreatedEvent {

    private final UUID id;
    private final BigDecimal initialBalance;
    private final String owner;
}
