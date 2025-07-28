package com.arbaaz.SpringSecurity11_1.repository;

import com.arbaaz.SpringSecurity11_1.model.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Accounts,Long> {

    @PreAuthorize("hasAuthority('USER')")
    Optional<Accounts> findByCustomer_CustomerId(Long id);
}
