package com.ssafy.townbook.model.service;

import com.ssafy.townbook.model.dto.BookLogDto;
import java.util.List;

public interface BookLogService {
    
    /**
     * 전체 북로그 조회
     *
     * @return List
     */
    List<BookLogDto> findAll();
    
    /**
     * 단일 북로그 조회
     *
     * @param bookLogNo
     * @return BookLogDto
     */
    BookLogDto findBookLogByBookLogNo(Long bookLogNo);
}
