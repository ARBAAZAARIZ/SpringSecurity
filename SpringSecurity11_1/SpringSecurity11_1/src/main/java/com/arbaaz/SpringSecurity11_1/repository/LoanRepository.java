package com.arbaaz.SpringSecurity11_1.repository;

import com.arbaaz.SpringSecurity11_1.model.Loans;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loans,Long> {
}
