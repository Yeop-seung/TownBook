package com.ssafy.townbook.model.service;

import com.ssafy.townbook.model.dto.BookLogDto;
import com.ssafy.townbook.model.dto.request.DonateBookRequestDto;
import com.ssafy.townbook.model.dto.request.ReceiveBookRequestDto;
import com.ssafy.townbook.model.dto.response.DonateBookLogResponseDto;
import com.ssafy.townbook.model.dto.response.ReceiveBookLogResponseDto;
import com.ssafy.townbook.model.entity.Account;
import com.ssafy.townbook.model.entity.BookLog;
import com.ssafy.townbook.model.entity.DetailLocker;
import com.ssafy.townbook.model.repository.AccountRepository;
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
    private AccountRepository accountRepository;
    
    @Autowired
    public BookLogServiceImpl(
            BookLogRepository bookLogRepository, BookLogQueryRepository bookLogQueryRepository,
            LockerRepository lockerRepository, DetailLockerRepository detailLockerRepository,
            AdminRepository adminRepository, BookRepository bookRepository, AccountRepository accountRepository) {
        this.bookLogRepository = bookLogRepository;
        this.bookLogQueryRepository = bookLogQueryRepository;
        this.lockerRepository = lockerRepository;
        this.detailLockerRepository = detailLockerRepository;
        this.adminRepository = adminRepository;
        this.bookRepository = bookRepository;
        this.accountRepository = accountRepository;
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
    public List<ReceiveBookLogResponseDto> findBookLogByLockerNo(Long lockerNo) {
        List<BookLog> findBookLogs = bookLogQueryRepository.findBookLogByLockerNo(lockerNo).get();
        return findBookLogs.stream()
                .map(ReceiveBookLogResponseDto::new)
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
    
    /**
     * 도서 기부
     *
     * @param donateBookRequestDto
     * @return AdminDto
     */
    @Override
    @Transactional
    public DonateBookLogResponseDto donateBook(DonateBookRequestDto donateBookRequestDto) throws Exception {
        BookLog bookLog = new BookLog();
        
        // account : point 100++, bookCnt++
        // point 100 + cnt 1
        Account account = adminRepository.findAccountByAccountNo(donateBookRequestDto.getAccountNo()).get();
        account.setAccountPoint(account.getAccountPoint() + 100);
        account.setAccountBookCnt(account.getAccountBookCnt() + 1);
        accountRepository.save(account);
        
        // locker
        bookLog.setLocker(lockerRepository.findLockerByLockerNo(donateBookRequestDto.getLockerNo()).get());
        
        // book
        bookLog.setBook(bookRepository.findBookByBookIsbn(donateBookRequestDto.getBookIsbn()).get());
        
        // bookLog
        bookLog.setBookLogDonateDateTime(LocalDateTime.now());
        
        // detailLocker : isEmpty false
        DetailLocker findDetailLocker = detailLockerRepository.findDetailLockerByDetailLockerNo(
                donateBookRequestDto.getDetailLockerNo()).get();
        findDetailLocker.setDetailLockerIsEmpty(false);
        
        bookLog.setDetailLocker(findDetailLocker);
        bookLog.setAccount(account);
        bookLogRepository.save(bookLog);
        
        // return AdminDto
        DonateBookLogResponseDto donateBookLogResponseDto = new DonateBookLogResponseDto(account.getAccountPoint());
        return donateBookLogResponseDto;
    }
    
    /**
     * 도서 수령
     *
     * @param receiveBookRequestDto
     * @return Boolean
     * @throws Exception
     */
    @Override
    @Transactional
    public boolean receiveBook(ReceiveBookRequestDto receiveBookRequestDto) throws Exception {
        // detailLocker
        Long detailLockerNo = receiveBookRequestDto.getDetailLockerNo();
        DetailLocker detailLocker = detailLockerRepository.findDetailLockerByDetailLockerNo(detailLockerNo).get();
        detailLocker.setDetailLockerIsEmpty(true);
        detailLockerRepository.save(detailLocker);
        
        // account
        Account account = accountRepository.findByAccountNo(receiveBookRequestDto.getAccountNo()).get();
        if (account.getAccountPoint() < 200) {
            System.out.println("포인트가 부족합니다");
            return false;
        }
        account.setAccountPoint(account.getAccountPoint() - 200);
        accountRepository.save(account);
        
        // bookLog
        BookLog bookLog = bookLogQueryRepository.findBookLogByDetailLockerNo(detailLockerNo).get().get(0);
        bookLog.setBookLogState(false);
        bookLog.setBookLogReceiveDateTime(LocalDateTime.now());
        bookLog.setBookLogReceiverNo(receiveBookRequestDto.getAccountNo());
        
        bookLogRepository.save(bookLog);
        return true;
    }
}
