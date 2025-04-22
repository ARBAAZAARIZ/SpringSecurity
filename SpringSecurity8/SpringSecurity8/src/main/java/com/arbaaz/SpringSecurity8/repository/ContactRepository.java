package com.arbaaz.SpringSecurity8.repository;

import com.arbaaz.SpringSecurity8.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact,Long> {
}
