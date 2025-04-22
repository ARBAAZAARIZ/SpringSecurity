package com.arbaaz.SpringSecurity5.repository;

import com.arbaaz.SpringSecurity5.model.AdminUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminUserRepository extends JpaRepository<AdminUser,Integer> {

    Optional<AdminUser> findByName(String name);
}
