package com.arbaaz.SpringSecurity8.repository;

import com.arbaaz.SpringSecurity8.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CardRepository extends JpaRepository<Card,Long> {


    @Query("SELECT c FROM Card c WHERE c.customer.customerId=:customerId")
    List<Card> findByCustomer(@Param("customerId") long customerId);


}
