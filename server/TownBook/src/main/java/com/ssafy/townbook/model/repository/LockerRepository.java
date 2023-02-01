package com.ssafy.townbook.model.repository;

import com.ssafy.townbook.model.entity.Locker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LockerRepository extends JpaRepository<Locker, Long> {
    
    /**
     * 단일 보관함 및 보관함에 할당된 세부 보관함 조회
     * 보관함 DTO로 변환하여 반환
     *
     * @param lockerNo
     * @return LockerDto
     */
    Optional<Locker> findLockerByLockerNo(Long lockerNo);
}
