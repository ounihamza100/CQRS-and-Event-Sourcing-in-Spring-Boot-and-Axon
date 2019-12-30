package com.hamzaouni.cqrseventsourcigwithspringbootaxon.aggregates;


import com.hamzaouni.cqrseventsourcigwithspringbootaxon.commands.CreditMoneyCommand;
import com.hamzaouni.cqrseventsourcigwithspringbootaxon.commands.DebitMoneyCommand;
import com.hamzaouni.cqrseventsourcigwithspringbootaxon.entities.BankAccount;
import com.hamzaouni.cqrseventsourcigwithspringbootaxon.commands.CreateAccountCommand;
import com.hamzaouni.cqrseventsourcigwithspringbootaxon.events.AccountCreatedEvent;
import com.hamzaouni.cqrseventsourcigwithspringbootaxon.events.MoneyCreditedEvent;
import com.hamzaouni.cqrseventsourcigwithspringbootaxon.events.MoneyDebitedEvent;
import com.hamzaouni.cqrseventsourcigwithspringbootaxon.exception.InsufficientBalanceException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * <em>What is an aggregate :</em>
 *     <p>An Aggregate is a regular object, which contains state and methods to alter that state.
 *     <p>An Aggregate is a business entity or group of business entities
 *     that is always kept in a consistent state (within a single ACID transaction).
 *     The Aggregate Root is the business entity within the aggregate that is responsible for maintaining this consistent state.
 *
 *  <t>For example: an aggregate can be an e-Commerce Order with the related OrderItems and Customer information.
 *
 * BookAccountAggregate will contain more Axon boilerplate code which cannot fit to a JPA Entity class (in our case {@link BankAccount}),
 * which is used only to represent data stored in a DB.
 *
 * @author Hamza Ouni
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Aggregate
public class BankAccountAggregate {
    @AggregateIdentifier
    private UUID id;
    private BigDecimal balance;
    private String owner;

    /**
     *  - The @CommandHandler will mark this method (constructor) as a Handler of the CreateAccountCommand.
     *  - The AggregateLifecycle component is used to notify the Aggregate that a new BankAccount was created by publishing the AccountCreatedEvent.
     */
    @CommandHandler
    public BankAccountAggregate(CreateAccountCommand command) {

        AggregateLifecycle.apply(
                new AccountCreatedEvent(
                        command.getAccountId(),
                        command.getInitialBalance(),
                        command.getOwner()
                )
        );
    }

    /**
     * The same way, if we dispatched a Command, we defined its CommandHandler.
     * Now, as we dispatched an Event, we need to define the EventHandler
     * The @EventSourcingHandler will define the annotated method as a handler for Events generated by that Aggregate.
     * @param event
     */
    @EventSourcingHandler
    public void on(AccountCreatedEvent event) {
        this.id = event.getId();
        this.owner = event.getOwner();
        this.balance = event.getInitialBalance();
    }

    @CommandHandler
    public void handle(CreditMoneyCommand command) {
        AggregateLifecycle.apply(
                new MoneyCreditedEvent(
                        command.getAccountId(),
                        command.getCreditAmount()
                )
        );
    }

    @EventSourcingHandler
    public void on(MoneyCreditedEvent event) {
        this.balance = this.balance.add(event.getCreditAmount());
    }

    @CommandHandler
    public void handle(DebitMoneyCommand command) {
        AggregateLifecycle.apply(
                new MoneyDebitedEvent(
                        command.getAccountId(),
                        command.getDebitAmount()
                )
        );
    }

    @EventSourcingHandler
    public void on(MoneyDebitedEvent event) throws InsufficientBalanceException {
        if (this.balance.compareTo(event.getDebitAmount()) < 0) {
            throw new InsufficientBalanceException(event.getId(), event.getDebitAmount());
        }
        this.balance = this.balance.subtract(event.getDebitAmount());
    }
}