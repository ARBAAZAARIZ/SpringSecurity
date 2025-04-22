package com.arbaaz.SpringSecurity8.repository;

import com.arbaaz.SpringSecurity8.model.Notices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NoticeRepository extends JpaRepository<Notices,Integer> {


    @Query("SELECT n FROM Notices n WHERE CURRENT_DATE BETWEEN n.noticeBegDt AND n.noticeEndDt")
    List<Notices> findByActiveNotice();
}
