package com.arbaaz.SpringSecurity6.repository;

import com.arbaaz.SpringSecurity6.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfo,Integer> {

    Optional<UserInfo> findByName(String name);
}
