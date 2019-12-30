package com.hamzaouni.cqrseventsourcigwithspringbootaxon.rest.dto;

import lombok.Value;

import java.math.BigDecimal;

/**
 * @author Hamza Ouni
 */
@Value
public class AccountCreationDTO {
    private final BigDecimal initialBalance;
    private final String owner;
}

