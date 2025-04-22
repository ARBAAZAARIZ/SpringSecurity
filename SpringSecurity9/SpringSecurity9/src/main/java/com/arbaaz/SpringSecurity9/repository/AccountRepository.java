package com.arbaaz.SpringSecurity9.repository;

import com.arbaaz.SpringSecurity9.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account , Long> {

    @Query("SELECT a FROM Account a WHERE a.customer.id = :id")
    Account findByCustomerId(@Param("id") long id);

}
