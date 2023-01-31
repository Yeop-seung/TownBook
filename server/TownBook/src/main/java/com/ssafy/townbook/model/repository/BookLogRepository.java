package com.ssafy.townbook.model.repository;

import com.ssafy.townbook.model.entity.BookLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookLogRepository extends JpaRepository<BookLog, Long> {
    
    BookLog findBookLogByBookLogNo(Long bookLogNo);
}
