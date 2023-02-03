package com.ssafy.townbook.model.repository;

import com.ssafy.townbook.model.entity.BookLog;
import com.ssafy.townbook.model.entity.DetailLocker;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetailLockerRepository extends JpaRepository<DetailLocker, Long> {
    
    Optional<DetailLocker> findDetailLockerByDetailLockerNo(Long detailLockerNo);
}
