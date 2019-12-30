package com.hamzaouni.cqrseventsourcigwithspringbootaxon.rest.dto;

import lombok.*;

import java.math.BigDecimal;

/**
 * @author Hamza Ouni
 */


public class AccountCreationDTO {
    private  BigDecimal initialBalance;
    private  String owner;

    public AccountCreationDTO() {
    }

    public AccountCreationDTO(BigDecimal initialBalance, String owner) {
        this.initialBalance = initialBalance;
        this.owner = owner;
    }

    public BigDecimal getInitialBalance() {
        return initialBalance;
    }

    public String getOwner() {
        return owner;
    }
}

