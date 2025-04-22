package com.arbaaz.SpringSecurity9.repository;

import com.arbaaz.SpringSecurity9.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByUsername(String username) ;
}
