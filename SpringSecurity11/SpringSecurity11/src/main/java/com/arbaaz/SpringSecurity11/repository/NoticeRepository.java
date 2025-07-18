package com.arbaaz.SpringSecurity11.repository;

import com.arbaaz.SpringSecurity11.model.Notices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeRepository extends JpaRepository<Notices,Integer> {

    @Query("SELECT n FROM Notices n WHERE CURRENT_DATE BETWEEN n.noticeBegDt AND n.noticeEndDt")
    List<Notices> findByActiveNotice();
}
