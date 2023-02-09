package com.ssafy.townbook.model.repository;

import com.ssafy.townbook.model.entity.BookLog;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookLogRepository extends JpaRepository<BookLog, Long> {
    
    /**
     * 단일 북로그 조회
     *
     * @param bookLogNo
     * @return Optional<BookLog>
     */
    Optional<BookLog> findBookLogByBookLogNo(Long bookLogNo);
}
