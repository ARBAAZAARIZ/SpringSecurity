package com.arbaaz.SpringSecurity9.repository;

import com.arbaaz.SpringSecurity9.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
}
