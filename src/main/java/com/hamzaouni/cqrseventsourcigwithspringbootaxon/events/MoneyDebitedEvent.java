package com.hamzaouni.cqrseventsourcigwithspringbootaxon.events;

import lombok.Value;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * @author Hamza Ouni
 */
@Value
public class MoneyDebitedEvent {

    private final UUID id;
    private final BigDecimal debitAmount;
}
