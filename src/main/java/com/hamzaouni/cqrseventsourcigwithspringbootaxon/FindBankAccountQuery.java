package com.hamzaouni.cqrseventsourcigwithspringbootaxon;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * @author Hamza Ouni
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FindBankAccountQuery {
    private UUID accountId;
}
