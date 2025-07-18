package com.arbaaz.SpringSecurity11.repository;

import com.arbaaz.SpringSecurity11.model.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Accounts,Long> {


    @Query("SELECT a FROM Accounts a WHERE a.customer.customerId=:customerId")
    Optional<Accounts> findByCustomerId( @Param("customerId") long customerId);
}
