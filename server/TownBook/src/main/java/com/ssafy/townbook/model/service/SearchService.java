package hide information.townbook.model.service;

import hide information.townbook.model.dto.response.FindListResponseDto;

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
