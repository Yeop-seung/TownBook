package com.ssafy.townbook.model.repository;

import com.ssafy.townbook.model.entity.DetailLocker;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetailLockerRepository extends JpaRepository<DetailLocker, Long> {
    
    List<DetailLocker> findDetailLockerByDetailLockerNo(Long detailLockerNo);
}
