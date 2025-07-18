package com.arbaaz.SpringSecurity11.repository;

import com.arbaaz.SpringSecurity11.model.NexusCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NexusCustomerRepository extends JpaRepository<NexusCustomer,Long> {

    Optional<NexusCustomer> findByEmail(String email);

//
//    @Query("SELECT c FROM NexusCustomer c WHERE LOWER(c.email) = LOWER(:email)")
//    Optional<NexusCustomer> findByEmail(@Param("email") String email);
}
