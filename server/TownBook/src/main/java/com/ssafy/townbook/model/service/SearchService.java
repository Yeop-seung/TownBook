package com.ssafy.townbook.model.service;

import com.ssafy.townbook.model.dto.BookLogDto;
import java.util.List;

public interface SearchService {
    
    /**
     * 제목 검색해서 북로그 반환
     *
     * @param bookTitle
     * @return List<BookLogDto>
     */
    List<BookLogDto> findBookLogByBookTitle(String bookTitle);
}
