package com.amit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amit.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
