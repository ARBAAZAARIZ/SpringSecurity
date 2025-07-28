package com.arbaaz.SpringSecurity11_1.repository;

import com.arbaaz.SpringSecurity11_1.model.Loans;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loans,Long> {

    @PreAuthorize("hasAuthority('USER')")
    List<Loans> findByCustomer_CustomerId(Long customerId);


}
