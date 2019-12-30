package com.hamzaouni.cqrseventsourcigwithspringbootaxon.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.UUID;

/**
 *
 * <em>COMMANDS AND QUERIES
 * our commands will be:
 *  <li>CreateAccountCommand</li>
 *  <li>CreditMoneyCommand</li>
 *  <li>DebitMoneyCommand</li>
 *
 * For the queries:
 *  <li>FindAccountQuery</li>
 * @author Hamza Ouni
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BankAccount {
    @Id
    private UUID id;
    private String owner;
    private BigDecimal balance;
}
