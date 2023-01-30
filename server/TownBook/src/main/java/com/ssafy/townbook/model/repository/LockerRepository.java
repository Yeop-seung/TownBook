package com.ssafy.townbook.model.repository;

import com.ssafy.townbook.model.entity.Locker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LockerRepository extends JpaRepository<Locker, Long> {
    
    Locker findLockerByLockerNo(Long lockerNo);
}
