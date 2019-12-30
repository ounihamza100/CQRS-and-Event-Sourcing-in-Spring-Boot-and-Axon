package com.hamzaouni.cqrseventsourcigwithspringbootaxon.rest.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author Hamza Ouni
 */
@Data
@NoArgsConstructor
public class MoneyAmountDTO {
    private BigDecimal amount;
}
