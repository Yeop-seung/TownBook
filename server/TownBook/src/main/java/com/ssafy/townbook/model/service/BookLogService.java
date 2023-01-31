package com.ssafy.townbook.model.service;

import com.ssafy.townbook.model.dto.BookLogDto;
import java.util.List;
import java.util.Optional;

public interface BookLogService {
    
    List<BookLogDto> findAll();
    
    BookLogDto findBookLogByBookLogNo(Long bookLogNo);
}
