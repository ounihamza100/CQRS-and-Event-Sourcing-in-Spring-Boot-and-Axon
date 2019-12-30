package com.hamzaouni.cqrseventsourcigwithspringbootaxon.events;

import lombok.Value;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * @author Hamza Ouni
 */
@Value
public class MoneyCreditedEvent {

    private final UUID id;
    private final BigDecimal creditAmount;
}
