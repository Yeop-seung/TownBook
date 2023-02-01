package com.ssafy.townbook;

import com.ssafy.townbook.model.entity.Account;
import com.ssafy.townbook.model.entity.Authority;
import com.ssafy.townbook.model.entity.Book;
import com.ssafy.townbook.model.entity.BookLog;
import com.ssafy.townbook.model.entity.DetailLocker;
import com.ssafy.townbook.model.entity.Locker;
import com.ssafy.townbook.model.repository.AccountRepository;
import com.ssafy.townbook.model.repository.BookLogRepository;
import com.ssafy.townbook.model.repository.BookRepository;
import com.ssafy.townbook.model.repository.LockerRepository;
import com.ssafy.townbook.model.service.BookService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Optional;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * 프로젝트 실행하면
 * 1. 책
 * 2. 계정
 * 3. 인증
 * 4. 계정 + 인증
 * 5. 보관함
 * 6. 세부 보관함
 * 생성됨
 */
@Component
@RequiredArgsConstructor
public class InitDb {
    
    public final InitService initService;
    
    @PostConstruct
    public void init() {
        initService.bookInit();
//        initService.accountInit();
        initService.lockerInit();
        initService.bookLogInit();
    }
    
    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        
        public final EntityManager em;
        public final BookService bookService;
        public final LockerRepository lockerRepository;
        private final AccountRepository accountRepository;
        private final BookRepository bookRepository;
        private final BookLogRepository bookLogRepository;
        
        public void bookInit() {
            Book book1 = createBook("8984993751", "8", "토지", "박경리", "커뮤니케이션 북스", convertDate("20051103"),
                    null, null);
            em.persist(book1);
            
            Book book2 = createBook("9788960777330", "0",
                    "자바 ORM 표준 JPA 프로그래밍(스프링 데이터 예제 프로젝트로 배우는 전자정부 표준 데이터베이스 프레임워크)",
                    "김영한", "에이콘출판", convertDate("20150728"),
                    "https://www.nl.go.kr/seoji/fu/ecip/dbfiles/CIP_FILES_TBL/4721454_5.txt"
                    , "https://www.nl.go.kr/seoji/fu/ecip/dbfiles/CIP_FILES_TBL/4721454_3.jpg");
            em.persist(book2);
        }
        
        public Book createBook(String bookIsbn, String bookSubject, String bookTitle, String bookAuthor,
                String bookPublisher, LocalDate bookPublishPredate, String bookIntroductionURL, String bookTitleURL) {
            Book book = new Book();
            book.setBookIsbn(bookIsbn);
            book.setBookSubject(bookSubject);
            book.setBookTitle(bookTitle);
            book.setBookVol(0);
            book.setBookAuthor(bookAuthor);
            book.setBookPublisher(bookPublisher);
            book.setBookPublishPredate(bookPublishPredate);
            book.setBookIntroductionURL(bookIntroductionURL);
            book.setBookTitleURL(bookTitleURL);
            return book;
        }
        
        
        public void accountInit() {
            Authority authorityRoleUser = new Authority();
            authorityRoleUser.setAuthorityName("ROLE_USER");
            Authority authorityRoleAdmin = new Authority();
            authorityRoleAdmin.setAuthorityName("ROLE_ADMIN");
            em.persist(authorityRoleUser);
            em.persist(authorityRoleAdmin);
            
            Account account1 = createAccount("test@test.com", "password", "김싸피", "대전시 유성구 덕명동", "010-1234-5678", 0,
                    "내가 바로 김싸피", "220222", authorityRoleUser);
            em.persist(account1);
            Account account2 = createAccount("admin@test.com", "adminPassword", "최어드", "대전시 유성구 어드동", "010-5678-1234",
                    1, "내가 바로 최어드", "111111", authorityRoleAdmin);
            em.persist(account2);
        }
        
        public Account createAccount(String accountEmail, String accountPw, String accountName, String accountAddress,
                String accountPhoneNumber, Integer accountGender, String accountNickname, String accountBirthDay,
                Authority authority) {
            Account account = new Account();
            account.setAccountEmail(accountEmail);
            account.setAccountPw(accountPw);
            account.setAccountName(accountName);
            account.setAccountAddress(accountAddress);
            account.setAccountPhoneNumber(accountPhoneNumber);
            account.setAccountGender(accountGender);
            account.setAccountNickname(accountNickname);
            account.setAccountBirthDay(accountBirthDay);
            account.setAuthorities(Collections.singleton(authority));
            return account;
        }
        
        public void lockerInit() {
            createLocker("어은동", 1, "12312312", "12312312");
            createLocker("덕명동", 3, "32132132", "12312312");
            createLocker("봉명동", 5, "12345678", "12312312");
        }
        
        public void createLocker(String lockerRegion, int detailLockerCount, String lockerLatitude,
                String lockerLongitude) {
            Locker locker = new Locker();
            locker.setLockerRegion(lockerRegion);
            locker.setLockerLatitude(lockerLatitude);
            locker.setLockerLongitude(lockerLongitude);
            em.persist(locker);
            
            while (detailLockerCount-- > 0) {
                DetailLocker detailLocker = new DetailLocker();
                locker.addDetailLocker(detailLocker);
                em.persist(detailLocker);
            }
        }
        
        public void bookLogInit() {
            Locker locker1 = lockerRepository.findLockerByLockerNo(1L).get();
            DetailLocker detailLocker1 = locker1.getDetailLocker().get(0);
            Optional<Account> account1 = accountRepository.findByAccountNo(1L);
            Optional<Book> book1 = bookRepository.findBookByBookIsbn("8984993751");
            donateBook("재밌어요", locker1, detailLocker1, account1, book1);
            
            Locker locker2 = lockerRepository.findLockerByLockerNo(2L).get();
            DetailLocker detailLocker2 = locker2.getDetailLocker().get(0);
            Optional<Account> account2 = accountRepository.findByAccountNo(2L);
            Optional<Book> book2 = bookRepository.findBookByBookIsbn("9788960777330");
            donateBook("재미 없어요", locker2, detailLocker2, account2, book2);
            
            BookLog bookLog1 = bookLogRepository.findBookLogByBookLogNo(2L);
            receiveBook(bookLog1, account1);
        }
        
        public void donateBook(
                String bookLogReview, Locker locker, DetailLocker detailLocker,
                Optional<Account> account, Optional<Book> book) {
            BookLog bookLog = new BookLog();
            bookLog.setBookLogReview(bookLogReview);
            bookLog.setBookLogDonateDateTime(LocalDateTime.now());
            bookLog.setLocker(locker);
            bookLog.setDetailLocker(detailLocker);
            bookLog.setAccount(account.get());
            bookLog.setBook(book.get());
            em.persist(bookLog);
        }
        
        public void receiveBook(BookLog bookLog, Optional<Account> account) {
            bookLog.setBookLogState(false);
            bookLog.setBookLogReceiverNo(account.get().getAccountNo());
            bookLog.setBookLogReceiveDateTime(LocalDateTime.now());
            em.persist(bookLog);
        }
    }
    
    static LocalDate convertDate(String dateString) {
        LocalDate localDate = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyyMMdd"));
        return localDate;
    }
}
