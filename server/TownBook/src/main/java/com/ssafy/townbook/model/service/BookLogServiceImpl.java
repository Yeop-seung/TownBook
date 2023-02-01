package com.ssafy.townbook.model.service;

import com.ssafy.townbook.model.dto.BookDto;
import com.ssafy.townbook.model.dto.BookLogDto;
import com.ssafy.townbook.model.entity.Book;
import com.ssafy.townbook.model.entity.BookLog;
import com.ssafy.townbook.model.repository.BookLogRepository;
import com.ssafy.townbook.queryrepository.BookLogQueryRepository;
import java.util.List;
import java.util.Optional;
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
     * @return List<BookLogDto>
     */
    @Override
    public List<BookLogDto> findAll() {
        Optional<List<BookLog>> findBookLogs = Optional.ofNullable(bookLogRepository.findAll());
        return findBookLogs.get().stream()
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
        return new BookLogDto(bookLogRepository.findBookLogByBookLogNo(bookLogNo).get());
    }
    
    /**
     * 단일 보관함에 보관중인 도서 전부 조회
     *
     * @param lockerNo
     * @return List<BookDto>
     */
    @Override
    public List<BookDto> findBookByLockerNo(Long lockerNo) {
        List<Book> findBooks = bookLogQueryRepository.findBookByLockerNo(lockerNo).get();
        return findBooks.stream()
                .map(BookDto::new)
                .collect(Collectors.toList());
    }
    
    /**
     * 단일 도서의 모든 리뷰 조회
     *
     * @param bookIsbn
     * @return List<BookLogDto.bookLogReview>
     */
    public List<String> findBookLogReviewByBookIsbn(String bookIsbn) {
        List<String> findReviews = bookLogQueryRepository.findBookLogReviewByBookIsbn(bookIsbn).get();
        return findReviews;
    }
}
