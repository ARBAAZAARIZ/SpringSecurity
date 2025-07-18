package com.arbaaz.SpringSecurity8.repository;

import com.arbaaz.SpringSecurity8.model.AccountTransactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountTransactionRepository extends JpaRepository<AccountTransactions,String> {


        @Query("SELECT at FROM AccountTransactions at WHERE at.customer.customerId=:customerId ORDER BY at.transactionDt DESC")
        List<AccountTransactions> findByCustomerOrderByTransactionDtDesc( @Param("customerId") long customerId);
}
