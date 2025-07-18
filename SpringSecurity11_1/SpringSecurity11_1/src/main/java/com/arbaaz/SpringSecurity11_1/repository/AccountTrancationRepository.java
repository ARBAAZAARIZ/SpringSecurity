package com.arbaaz.SpringSecurity11_1.repository;

import com.arbaaz.SpringSecurity11_1.model.AccountTransactions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountTrancationRepository extends JpaRepository<AccountTransactions,String> {
}
