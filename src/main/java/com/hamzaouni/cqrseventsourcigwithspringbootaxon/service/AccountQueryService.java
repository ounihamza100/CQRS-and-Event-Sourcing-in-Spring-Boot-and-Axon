package com.hamzaouni.cqrseventsourcigwithspringbootaxon.service;

import com.hamzaouni.cqrseventsourcigwithspringbootaxon.FindBankAccountQuery;
import com.hamzaouni.cqrseventsourcigwithspringbootaxon.entities.BankAccount;
import lombok.AllArgsConstructor;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import org.axonframework.messaging.Message;
import static com.hamzaouni.cqrseventsourcigwithspringbootaxon.service.ServiceUtils.formatUuid;

/**
 * @author Hamza Ouni
 */
@Service
@AllArgsConstructor
public class AccountQueryService {
    private final QueryGateway queryGateway;
    private final EventStore eventStore;

    public CompletableFuture<BankAccount> findById(String accountId) {
        return this.queryGateway.query(
                new FindBankAccountQuery(formatUuid(accountId)),
                ResponseTypes.instanceOf(BankAccount.class)
        );
    }

    public List<Object> listEventsForAccount(String accountId) {
        return this.eventStore
                .readEvents(formatUuid(accountId).toString())
                .asStream()
                .map(Message::getPayload)
                .collect(Collectors.toList());
    }
}

