package com.arbaaz.SpringSecurity8.repository;

import com.arbaaz.SpringSecurity8.model.Loans;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoansRepository extends JpaRepository<Loans,Long> {

    @Query("SELECT l FROM Loans l WHERE l.customer.customerId=:customerId ORDER BY l.startDt DESC")
    List<Loans> findByCustomerIdOrderByStartDtDesc( @Param("customerId") long customerId);
}
