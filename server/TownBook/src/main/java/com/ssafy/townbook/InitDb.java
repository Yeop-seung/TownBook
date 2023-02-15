package com.ssafy.townbook;

import com.ssafy.townbook.model.entity.Account;
import com.ssafy.townbook.model.entity.Authority;
import com.ssafy.townbook.model.entity.Book;
import com.ssafy.townbook.model.entity.BookLog;
import com.ssafy.townbook.model.entity.DetailLocker;
import com.ssafy.townbook.model.entity.Locker;
import com.ssafy.townbook.model.entity.Notice;
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
import org.springframework.security.crypto.password.PasswordEncoder;
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
        initService.accountInit();
        initService.lockerInit();
        initService.bookLogInit();
        initService.noticeInit();
    }
    
    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        
        public final  EntityManager     em;
        public final  BookService       bookService;
        public final  LockerRepository  lockerRepository;
        private final AccountRepository accountRepository;
        private final BookRepository    bookRepository;
        private final BookLogRepository bookLogRepository;
        private final PasswordEncoder   passwordEncoder;
        
        public void bookInit() {
            Book book1 = createBook("9788966260959", "0", "클린코드", "로버트 마틴", "인사이트", convertDate("20131224"),
                    "URL이 없습니다.", "http://www.nl.go.kr/seoji/fu/ecip/dbfiles/CIP_FILES_TBL/3164716_3.jpg");
            em.persist(book1);
            
            Book book2 = createBook("9788960777330", "0",
                    "자바 ORM 표준 JPA 프로그래밍(스프링 데이터 예제 프로젝트로 배우는 전자정부 표준 데이터베이스 프레임워크)",
                    "김영한", "에이콘출판", convertDate("20150728"),
                    "https://www.nl.go.kr/seoji/fu/ecip/dbfiles/CIP_FILES_TBL/4721454_5.txt"
                    , "https://www.nl.go.kr/seoji/fu/ecip/dbfiles/CIP_FILES_TBL/4721454_3.jpg");
            em.persist(book2);
            
            Book book3 = createBook("9788966262670", "0",
                    "실전 리액트 프로그래밍", "이재승 지음", "인사이트", convertDate("20200713"), "URL이 없습니다.",
                    "https://www.nl.go.kr/seoji/fu/ecip/dbfiles/CIP_FILES_TBL/2020/06/08/9788966262670.jpg");
            em.persist(book3);
            
            Book book4 = createBook("9791158510619", "1",
                    "타이탄의 도구들", "팀 페리스", "토네이도미디어그룹(주)", convertDate("20170403"),
                    "URL이 없습니다.", "http://www.nl.go.kr/seoji/fu/ecip/dbfiles/CIP_FILES_TBL/6143212_3.jpg");
            em.persist(book4);
            
            Book book5 = createBook("9791190313186", "1", "지적 대화를 위한 넓고 얕은 지식 ", "채사장", "Whalebooks",
                    convertDate("20200120"), "URL이 없습니다.",
                    "https://www.nl.go.kr/seoji/fu/ecip/dbfiles/CIP_FILES_TBL/2020/01/07/9791190313186.jpg");
            em.persist(book5);
        }
        
        
        public Book createBook(String bookIsbn, String bookSubject, String bookTitle, String bookAuthor,
                String bookPublisher, LocalDate bookPublishPredate, String bookIntroductionURL,
                String bookTitleURL) {
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
            Authority authorityRoleUser  = createAuthority("ROLE_USER");
            Authority authorityRoleAdmin = createAuthority("ROLE_ADMIN");
            em.persist(authorityRoleUser);
            em.persist(authorityRoleAdmin);
            
            Account account1 = createAccount("nonmember@townbook.com", passwordEncoder.encode("password"), "비회원", "",
                    "", 0, "비회원 계정", "000000", authorityRoleUser);
            account1.setAccountPoint(99999999);
            em.persist(account1);
            
            Account account2 = createAccount("test@townbook.com", passwordEncoder.encode("password"), "김싸피",
                    "대전시 유성구 덕명동",
                    "01012345678", 0, "내가 바로 김싸피", "951221", authorityRoleUser);
            account2.setAccountPoint(1000);
            em.persist(account2);
            
            Account account3 = createAccount("admin@townbook.com", passwordEncoder.encode("password"), "최어드",
                    "대전시 유성구 어드동",
                    "01056781234", 1, "내가 바로 최어드", "931006", authorityRoleAdmin);
            em.persist(account3);
            
            Account account4 = createAccount("support@townbook.com", passwordEncoder.encode("password"), "박지원",
                    "대전시 유성구 봉명동", "01045454545", 1, "내가 바로 박지원", "970128", authorityRoleUser);
            account4.setAccountType(1);
            em.persist(account4);
        }
        
        public Authority createAuthority(String authorityName) {
            Authority authority = new Authority();
            authority.setAuthorityName(authorityName);
            return authority;
        }
        
        public Account createAccount(String accountEmail, String accountPw, String accountName, String accountAddress,
                String accountPhoneNumber, Integer accountGender, String accountNickname,
                String accountBirthDay,
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
            createLocker("Init", 0, 0D, 0D);
            createLocker("Init", 0, 0D, 0D);
            createLocker("역삼동", 2, 37.5021D, 127.0396D);
            createLocker("덕명동", 2, 36.3552D, 127.2984D);
            createLocker("공단동", 2, 36.0988D, 128.3897D);
            createLocker("송정동", 2, 35.0955D, 128.8556D);
            createLocker("오선동", 2, 35.2042D, 126.8072D);
        }
        
        public void createLocker(String lockerRegion, int detailLockerCount, Double lockerLatitude,
                Double lockerLongitude) {
            Locker locker = new Locker();
            locker.setLockerRegion(lockerRegion);
            locker.setLockerLatitude(lockerLatitude);
            locker.setLockerLongitude(lockerLongitude);
            em.persist(locker);
            
            for (int i = 1; i <= detailLockerCount; i++) {
                DetailLocker detailLocker = new DetailLocker();
                locker.addDetailLocker(detailLocker);
                detailLocker.setDetailLockerNoInLocker((long) i);
                detailLocker.setBookInDetailLocker(null);
                em.persist(detailLocker);
            }
        }
        
        public void bookLogInit() {
            Optional<Account> account1 = accountRepository.findByAccountNo(2L);
            Optional<Account> account2 = accountRepository.findByAccountNo(4L);
            
            // 3
            Locker         locker3       = lockerRepository.findLockerByLockerNo(3L).get();
            DetailLocker   detailLocker3 = locker3.getDetailLocker().get(0);
            Optional<Book> book3         = bookRepository.findBookByBookIsbn("9788966260959");
            donateBook("환상적인 책이예요", locker3, detailLocker3, account2, book3);
            
            // 4
            Locker         locker4       = lockerRepository.findLockerByLockerNo(4L).get();
            DetailLocker   detailLocker4 = locker4.getDetailLocker().get(0);
            Optional<Book> book4         = bookRepository.findBookByBookIsbn("9788966262670");
            donateBook("재미있어요", locker4, detailLocker4, account1, book4);
            
            // 5
            Locker         locker5       = lockerRepository.findLockerByLockerNo(5L).get();
            DetailLocker   detailLocker5 = locker5.getDetailLocker().get(0);
            Optional<Book> book5         = bookRepository.findBookByBookIsbn("9788960777330");
            donateBook("다시 읽고 싶지 않아요", locker5, detailLocker5, account2, book5);
            
            // 6
            Locker         locker6       = lockerRepository.findLockerByLockerNo(6L).get();
            DetailLocker   detailLocker6 = locker6.getDetailLocker().get(0);
            Optional<Book> book6         = bookRepository.findBookByBookIsbn("9791158510619");
            donateBook("너무 재밌어요", locker6, detailLocker6, account1, book6);
            
            // 7
            Locker         locker7       = lockerRepository.findLockerByLockerNo(7L).get();
            DetailLocker   detailLocker7 = locker7.getDetailLocker().get(0);
            Optional<Book> book7         = bookRepository.findBookByBookIsbn("9791190313186");
            donateBook("그닥 재미 없어요", locker7, detailLocker7, account1, book7);
        }
        
        public void donateBook(
                String bookLogReview, Locker locker, DetailLocker detailLocker,
                Optional<Account> account, Optional<Book> book) {
            BookLog bookLog = new BookLog(locker.getLockerNo(), detailLocker.getDetailLockerNo(), account.get()
                    .getAccountNo(), book.get().getBookIsbn());
            
            bookLog.setBookLogReview(bookLogReview);
            bookLog.setBookLogDonateDateTime(LocalDateTime.now());
            
            locker.setLockerBookCnt(locker.getLockerBookCnt() + 1);
            bookLog.setLocker(locker);
            
            detailLocker.setBookInDetailLocker(book.get().getBookTitle());
            bookLog.setDetailLocker(detailLocker);
            
            // account
            account.get().setAccountBookCnt(account.get().getAccountBookCnt() + 1);
            account.get().setAccountPoint(account.get().getAccountPoint() + 100);
            bookLog.setAccount(account.get());
            
            bookLog.setBook(book.get());
            em.persist(bookLog);
        }
        
        public void receiveBook(BookLog bookLog, Optional<Account> account) {
            bookLog.getLocker().setLockerBookCnt(bookLog.getLocker().getLockerBookCnt() - 1);
            bookLog.getDetailLocker().setBookInDetailLocker(null);
            bookLog.setBookLogState(false);
            
            // account
            account.get().setAccountPoint(account.get().getAccountPoint() - 200);
            bookLog.setBookLogReceiverNo(account.get().getAccountNo());
            
            bookLog.setBookLogReceiveDateTime(LocalDateTime.now());
            em.persist(bookLog);
        }
        
        public void noticeInit() {
            Long account = 3L;
            createNotice("2023년 02월 동네북 서비스 일정",
                    "2023년 02월 동네북 서비스 일정에 대해 공지 드립니다.", account);
            createNotice("2023년 02월 동네북 이벤트 안내",
                    "2023년 02월 동네북 이벤트에 대해 공지 드립니다.", account);
            createNotice("2023년 02월 신규 동네북 보관함 안내",
                    "2023년 02월 신규 동네북 보관함에 대해 공지 드립니다.", account);
            
            createGuide("내 주변 동네북 보관함 찾기",
                    "내 주변 동네북 보관함 찾는 방법", account);
            createGuide("비회원으로 도서 기부하기",
                    "비회원으로 도서 기부하는 방법", account);
            createGuide("회원 QR코드 발급 받기",
                    "회원 QR코드 발급 받는 방법", account);
        }
        
        public void createNotice(String noticeTitle, String noticeContent, Long account) {
            Notice notice = new Notice(account);
            notice.setNoticeTitle(noticeTitle);
            notice.setNoticeContent(noticeContent);
            notice.setNoticeWriteDateTime(LocalDateTime.now());
            notice.setNoticeCategory(0);
            em.persist(notice);
        }
        
        public void createGuide(String noticeTitle, String noticeContent, Long account) {
            Notice notice = new Notice(account);
            notice.setNoticeTitle(noticeTitle);
            notice.setNoticeContent(noticeContent);
            notice.setNoticeWriteDateTime(LocalDateTime.now());
            notice.setNoticeCategory(1);
            em.persist(notice);
        }
        
    }
    
    static LocalDate convertDate(String dateString) {
        LocalDate localDate = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyyMMdd"));
        return localDate;
    }
}