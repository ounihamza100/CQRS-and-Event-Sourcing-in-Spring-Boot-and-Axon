package com.hamzaouni.cqrseventsourcigwithspringbootaxon.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * For every Command made, we dispatch an Event.
 * For example : for the CreateAccountCommand we need to create an AccountCreatedEvent that will be used to say that a Command has been received.
 *
 * @author Hamza Ouni
 */
@Data               // Lombok
@NoArgsConstructor  // Lombok
@AllArgsConstructor // Lombok
public class CreateAccountCommand {

    @TargetAggregateIdentifier
    private UUID accountId;
    private BigDecimal initialBalance;
    private String owner;
}
