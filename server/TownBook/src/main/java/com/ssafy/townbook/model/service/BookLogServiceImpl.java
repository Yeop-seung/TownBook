package com.ssafy.townbook.model.service;

import com.ssafy.townbook.model.dto.BookDto;
import com.ssafy.townbook.model.dto.BookLogDto;
import com.ssafy.townbook.model.entity.Book;
import com.ssafy.townbook.model.entity.BookLog;
import com.ssafy.townbook.model.repository.BookLogRepository;
import com.ssafy.townbook.queryrepository.BookLogQueryRepository;
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
    private BookLogQueryRepository bookLogQueryRepository;
    
    @Autowired
    public BookLogServiceImpl(
            BookLogRepository bookLogRepository, BookLogQueryRepository bookLogQueryRepository) {
        this.bookLogRepository = bookLogRepository;
        this.bookLogQueryRepository = bookLogQueryRepository;
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
    
    /**
     * 단일 보관함에 보관중인 도서 전부 조회
     *
     * @param lockerNo
     * @return List
     */
    @Override
    public List<BookDto> findBookByLockerNo(Long lockerNo) {
        List<Book> findBooks = bookLogQueryRepository.findBookByLockerNo(lockerNo);
        return findBooks.stream()
                .map(BookDto::new)
                .collect(Collectors.toList());
    }
}
