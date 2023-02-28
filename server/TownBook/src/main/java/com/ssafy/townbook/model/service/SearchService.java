package com.ssafy.townbook.model.service;

import com.ssafy.townbook.model.dto.response.FindListResponseDto;

public interface SearchService {
    
    /**
     * 제목 검색해서 북로그 반환
     *
     * @param bookTitle
     * @return List<BookLogDto>
     */
    FindListResponseDto searchBookLogByBookTitle(String bookTitle);
    
    FindListResponseDto searchLockerByLockerNo(Long lockerNo);
}
