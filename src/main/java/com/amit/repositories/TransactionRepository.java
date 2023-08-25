package com.amit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amit.entities.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction,Integer> {

}
