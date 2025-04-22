package com.arbaaz.SpringSecurity8.repository;

import com.arbaaz.SpringSecurity8.model.NexusCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NexusCustomerRepository extends JpaRepository<NexusCustomer,Long> {

    Optional<NexusCustomer> findByEmail(String email);
}
