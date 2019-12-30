package com.hamzaouni.cqrseventsourcigwithspringbootaxon.repository;

import com.hamzaouni.cqrseventsourcigwithspringbootaxon.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * @author Hamza Ouni
 */
@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, UUID> {
}
