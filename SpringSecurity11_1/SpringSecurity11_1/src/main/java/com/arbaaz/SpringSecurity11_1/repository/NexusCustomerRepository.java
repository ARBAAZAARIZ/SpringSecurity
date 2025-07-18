package com.arbaaz.SpringSecurity11_1.repository;

import com.arbaaz.SpringSecurity11_1.model.NexusCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NexusCustomerRepository extends JpaRepository<NexusCustomer,Long> {

    Optional<NexusCustomer> findByEmail(String email);
}
