package com.amit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amit.entities.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {

}
