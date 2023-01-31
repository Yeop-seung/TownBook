package com.ssafy.townbook.model.service;

import com.ssafy.townbook.model.dto.BookLogDto;
import com.ssafy.townbook.model.entity.BookLog;
import com.ssafy.townbook.model.repository.BookLogRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BookLogServiceImpl implements BookLogService {
    
    private BookLogRepository bookLogRepository;
    
    @Autowired
    public BookLogServiceImpl(BookLogRepository bookLogRepository) {
        this.bookLogRepository = bookLogRepository;
    }
    
    /**
     * 전체 북로그 조회
     *
     * @return List
     */
    @Override
    public List<BookLogDto> findAll() {
        List<BookLog> findBookLogs = bookLogRepository.findAll();
        return findBookLogs.stream()
                .map(BookLogDto::new)
                .collect(Collectors.toList());
    }
    
    /**
     * 단일 북로그 조회
     *
     * @param bookLogNo
     * @return BookLogDto
     */
    @Override
    public BookLogDto findBookLogByBookLogNo(Long bookLogNo) {
        return new BookLogDto(bookLogRepository.findBookLogByBookLogNo(bookLogNo));
    }
}
