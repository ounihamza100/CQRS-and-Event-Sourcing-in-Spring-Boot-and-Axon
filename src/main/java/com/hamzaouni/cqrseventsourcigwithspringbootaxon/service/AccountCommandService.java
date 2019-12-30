package com.hamzaouni.cqrseventsourcigwithspringbootaxon.service;

import com.hamzaouni.cqrseventsourcigwithspringbootaxon.commands.CreateAccountCommand;
import com.hamzaouni.cqrseventsourcigwithspringbootaxon.commands.CreditMoneyCommand;
import com.hamzaouni.cqrseventsourcigwithspringbootaxon.commands.DebitMoneyCommand;
import com.hamzaouni.cqrseventsourcigwithspringbootaxon.entities.BankAccount;
import com.hamzaouni.cqrseventsourcigwithspringbootaxon.rest.dto.AccountCreationDTO;
import com.hamzaouni.cqrseventsourcigwithspringbootaxon.rest.dto.MoneyAmountDTO;
import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import static com.hamzaouni.cqrseventsourcigwithspringbootaxon.service.ServiceUtils.formatUuid;


/**
 * @author Hamza Ouni
 */
@Service
@AllArgsConstructor
public class AccountCommandService {
    private final CommandGateway commandGateway;

    public CompletableFuture<BankAccount> createAccount(AccountCreationDTO creationDTO) {
        return this.commandGateway.send(new CreateAccountCommand(
                UUID.randomUUID(),
                creationDTO.getInitialBalance(),
                creationDTO.getOwner()
        ));
    }

    public CompletableFuture<String> creditMoneyToAccount(String accountId,
                                                          MoneyAmountDTO moneyCreditDTO) {
        return this.commandGateway.send(new CreditMoneyCommand(
                formatUuid(accountId),
                moneyCreditDTO.getAmount()
        ));
    }

    public CompletableFuture<String> debitMoneyFromAccount(String accountId,
                                                           MoneyAmountDTO moneyDebitDTO) {
        return this.commandGateway.send(new DebitMoneyCommand(
                formatUuid(accountId),
                moneyDebitDTO.getAmount()
        ));
    }
}
