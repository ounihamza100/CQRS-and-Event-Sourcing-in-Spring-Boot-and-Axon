package com.hamzaouni.cqrseventsourcigwithspringbootaxon.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * @author Hamza Ouni
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DebitMoneyCommand {

    @TargetAggregateIdentifier
    private UUID accountId;
    private BigDecimal debitAmount;
}
