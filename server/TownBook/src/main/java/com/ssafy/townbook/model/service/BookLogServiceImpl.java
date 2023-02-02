package com.ssafy.townbook.model.service;

import com.ssafy.townbook.model.dto.BookDto;
import com.ssafy.townbook.model.dto.BookLogDto;
import com.ssafy.townbook.model.dto.request.DonateBookRequestDto;
import com.ssafy.townbook.model.entity.Book;
import com.ssafy.townbook.model.entity.BookLog;
import com.ssafy.townbook.model.repository.AdminRepository;
import com.ssafy.townbook.model.repository.BookLogRepository;
import com.ssafy.townbook.model.repository.BookRepository;
import com.ssafy.townbook.model.repository.DetailLockerRepository;
import com.ssafy.townbook.model.repository.LockerRepository;
import com.ssafy.townbook.queryrepository.BookLogQueryRepository;
import java.time.LocalDateTime;
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
    private LockerRepository lockerRepository;
    private DetailLockerRepository detailLockerRepository;
    private AdminRepository adminRepository;
    private BookRepository bookRepository;
    
    @Autowired
    public BookLogServiceImpl(
            BookLogRepository bookLogRepository, BookLogQueryRepository bookLogQueryRepository,
            LockerRepository lockerRepository, DetailLockerRepository detailLockerRepository,
            AdminRepository adminRepository, BookRepository bookRepository) {
        this.bookLogRepository = bookLogRepository;
        this.bookLogQueryRepository = bookLogQueryRepository;
        this.lockerRepository = lockerRepository;
        this.detailLockerRepository = detailLockerRepository;
        this.adminRepository = adminRepository;
        this.bookRepository = bookRepository;
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
    
    /**
     * 단일 회원의 모든 북로그 조회
     *
     * @param accountNo
     * @return List<BookLog>
     */
    public List<BookLogDto> findBookLogByAccountNo(Long accountNo) {
        List<BookLog> findBookLogs = bookLogQueryRepository.findBookLogByAccountNo(accountNo).get();
        return findBookLogs.stream()
                .map(BookLogDto::new)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional
    public boolean donateBook(DonateBookRequestDto donateBookRequestDto) {
        try {
            BookLog bookLog = new BookLog();
            bookLog.setBookLogDonateDateTime(LocalDateTime.now());
            bookLog.setLocker(lockerRepository.findLockerByLockerNo(donateBookRequestDto.getLockerNo()).get());
            bookLog.setDetailLocker(detailLockerRepository.findDetailLockerByDetailLockerNo(
                    donateBookRequestDto.getDetailLockerNo()).get());
            detailLockerRepository.findDetailLockerByDetailLockerNo(donateBookRequestDto.getDetailLockerNo()).get()
                    .setDetailLockerIsEmpty(false);
            bookLog.setAccount(adminRepository.findAccountByAccountNo(donateBookRequestDto.getAccountNo()).get());
            bookLog.setBook(bookRepository.findBookByBookIsbn(donateBookRequestDto.getBookIsbn()).get());
            bookLogRepository.save(bookLog);
            return true;
        } catch (Exception e) {
            e.getMessage();
            System.out.println(e.getMessage());
            return false;
        }
    }
}
