package com.arbaaz.SpringSecurity9.repository;

import com.arbaaz.SpringSecurity9.model.Notices;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticesRepository extends JpaRepository<Notices,Integer> {
}
