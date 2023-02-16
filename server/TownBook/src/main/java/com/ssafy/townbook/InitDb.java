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
                    "애자일 소프트웨어의 혁명적인 패러다임을 제시하는 책이다. 저자 로버트 마틴은 오브젝트 멘토(Object Mentor)의 동료들과 힘을 모아 ‘개발하며’ 클린 코드를 만드는 최상의 애자일 기법을 정제하여『Clean Code 클린 코드』에 담았다. 아주 많은 코드를 읽고 그 코드의 무엇이 옳은지, 그른지 생각하며 전문가로서 자신이 지니는 가치를 돌아보기 위해 꾸준히 노력한다면, 이 책을 통해 여러분의 프로그래밍 실력은 한층 더 높아질 것이다",
                    "http://www.nl.go.kr/seoji/fu/ecip/dbfiles/CIP_FILES_TBL/3164716_3.jpg");
            em.persist(book1);
            
            Book book2 = createBook("9788960777330", "0",
                    "자바 ORM 표준 JPA 프로그래밍(스프링 데이터 예제 프로젝트로 배우는 전자정부 표준 데이터베이스 프레임워크)",
                    "김영한", "에이콘출판", convertDate("20150728"),
                    "에이콘 오픈 소스 프로그래밍 시리즈. 자바 ORM 표준 JPA는 SQL 작성 없이 객체를 데이터베이스에 직접 저장할 수 있게 도와주고, 객체와 관계형 데이터베이스의 차이도 중간에서 해결해준다. 이 책은 JPA 기초 이론과 핵심 원리, 그리고 실무에 필요한 성능 최적화 방법까지 JPA에 대한 모든 것을 다룬다. 또한, 스프링 프레임워크와 JPA를 함께 사용하는 방법을 설명하고, 스프링 데이터 JPA, QueryDSL 같은 혁신적인 오픈 소스를 활용해서 자바 웹 애플리케이션을 효과적으로 개발하는 방법을 다룬다.\n"
                            + "\n"
                            + "JPA는 크게 객체와 테이블을 어떻게 매핑해야 하는지에 관한 설계 부분과 설계한 모델을 실제 사용하는 부분으로 나눌 수 있다. 책의 앞부분에서는 기초 이론과 설계 방법을 학습하고 뒷부분에서는 학습한 이론을 바탕으로 JPA를 활용해서 실제 웹 애플리케이션을 개발한다.\n"
                            + "\n"
                            + "이 책은 JPA를 사용해서 엔터프라이즈 애플리케이션을 개발하려는 모든 자바 개발자를 대상으로 한다. 이 책의 내용을 이해하려면 자바 언어와 JDBC를 사용한 데이터베이스 프로그래밍, 그리고 객체지향 프로그래밍과 관계형 데이터베이스에 대해 어느 정도 알고 있어야 한다. 추가로 3부는 웹 개발과 스프링 프레임워크에 대한 기초 지식이 필요하며 JUnit을 다룰 수 있어야 한다. 그리고 예제 코드를 실행하려면 메이븐에 대해서도 약간의 지식이 필요하다."
                    , "https://www.nl.go.kr/seoji/fu/ecip/dbfiles/CIP_FILES_TBL/4721454_3.jpg");
            em.persist(book2);
            
            Book book3 = createBook("9788966262670", "0",
                    "실전 리액트 프로그래밍", "이재승 지음", "인사이트", convertDate("20200713"), "이 책은 핵심 원리를 통해 리액트의 사용법을 알려준다. 단순히 기술을 소개하는 데 그치지 않고 그 기술을 사용하는 이유를 함께 다룬다. 따라서 리액트뿐 아니라 앞으로 만나게 될 어떤 기술도 빠르게 배울 수 있는 기본기를 쌓을 수 있다. 리액트를 이미 사용해 본 사람을 대상으로 쓰였지만 기초부터 시작한다. 우선 리액트 프로젝트를 구축하고, 최신 자바스크립트 문법, 리액트의 주요 개념을 간단히 알아본다. 실제 활용할 때 도움이 되는 고급 활용법, 고차 컴포넌트, 렌더 속성값 패턴을 다루고, 리액트 훅의 개념과 구현 방법을 알아본 후 리덕스, 바벨, 웹팩까지 다룬다. 서버사이드 렌더링의 개념과 Next.js 프로젝트를 구축하는 방법을 살펴본 후 타입스크립트를 이용해 리액트 코드를 작성한다. 추후 리액트에 추가될 것으로 예상되는 비동기 렌더링까지 다뤄서 다가올 변화에도 대비한다.",
                    "https://www.nl.go.kr/seoji/fu/ecip/dbfiles/CIP_FILES_TBL/2020/06/08/9788966262670.jpg");
            em.persist(book3);
            
            Book book4 = createBook("9791158510619", "1",
                    "타이탄의 도구들", "팀 페리스", "토네이도미디어그룹(주)", convertDate("20170403"),
                    "2017년 출간 즉시 아마존과 [뉴욕 타임스] 베스트셀러 차트 1위를 동시에 석권하며, 국내 독자들에게도 큰 사랑을 받은 《타이탄의 도구들》이 6년 연속 최고의 베스트셀러를 기념한 리커버 블랙 에디션으로 다시 우리를 찾아왔다. 출간 전부터 전 세계 독자들의 뜨거운 지지와 관심을 받았던 이 책에는 알랭 드 보통, 세스 고딘, 말콤 글래드웰, 파울로 코엘료, 피터 틸, 에드 캣멀 등등 이 시대 가장 성공한 인물들의 생생한 목소리가 담겨 있다. 세계적인 석학과 작가부터 최고의 혁신기업을 세운 창업가와 CEO, 크리에이티브 디렉터, 협상가, 슈퍼리치, 아티스트, 전문직 종사자까지 자기 분야에서 정상에 오른 사람들의 독창적인 성공 노하우가 낱낱이 담겨 있다.\n"
                            + "\n"
                            + "이 책의 저자 팀 페리스는 지난 3년간 자신의 팟캐스트 방송의 수백만 청취자와 함께 뽑은 ‘세상에서 가장 성공한 사람 200명’을 직접 만나, 그들의 삶을 집중 추적했다. 그들과 벌였던 심층 인터뷰와 열띤 토론, 그리고 그들이 직접 공개한 성공 비결들을 자신의 일상에 직접 적용해 탁월한 성과를 창출했던 경험을 망라해 이 한 권의 책에 담았다. 반응은 폭발적이었다. 그의 팟캐스트 방송은 아이튠스 비즈니스 분야 최초로 다운로드 수 1억 회를 돌파했고, 2016년 12월에 출간된 이 책은 3개월 만에 약 50만 부가 팔리는 대형 베스트셀러 반열에 올랐다.\n"
                            + "\n"
                            + "‘폭발적인 아이디어, 창조적인 습관과 디테일한 전략, 강력한 실행력’을 갖춘 그들을 팀 페리스는 거인이라는 뜻의 ‘타이탄(titan)’이라 명명했고, 이 책에 담긴 그들의 압도적인 성공은 우리가 어떤 목표에 어떤 방법으로 접근해야 할지에 대한 가장 지혜로운 길라잡이가 되어준다. 따라서 이 책의 독자들은 자기 삶의 가장 큰 터닝포인트와 혁신을 만들어낼 수 있는 놀라운 계획들을 만나게 될 것이다. 자신의 가장 큰 가능성을 통해 또 한 명의 ‘타이탄’으로 성장할 수 있는 기회와 힘을 선물받게 될 것이다.", "http://www.nl.go.kr/seoji/fu/ecip/dbfiles/CIP_FILES_TBL/6143212_3.jpg");
            em.persist(book4);
            
            Book book5 = createBook("9791190313186", "1", "지적 대화를 위한 넓고 얕은 지식 ", "채사장", "Whalebooks",
                    convertDate("20200120"), "어렵고 딱딱하던 인문학 분야 판도를 뒤바꾼 책. 출간 즉시 베스트셀러에 오르는 것을 시작으로, 200만 부 누적 판매를 돌파해 더블 밀리언셀러를 기록하며, 장장 5년간 스테디셀러의 자리에서 꿈쩍도 하지 않은 책. 발음하기도 낯설고 어려운 ‘지대넓얕’이라는 말을 유행시키고, 유사 콘셉트의 TV 프로그램까지 탄생시킨 책, 기초 상식에 목말라 있던 보통 사람들이 거리낌 없이 토론하게 하고 뉴스를 주체적으로 보게 하고 선거에서 주관을 갖게 한 책, 80대 독자가 독학을 시작하고 중학생 독자가 인문학을 읽게 하고 직장인들이 독서 모임을 갖게 하는 등 세대 불문 남녀노소 읽을 수 있도록 쉽고 재미있게 쓰였다고 평가받는 책. 이 모든 것이 『지적 대화를 위한 넓고 얕은 지식』에 대한 설명이다.\n"
                            + "\n"
                            + "신간 [제로] 편에 이어, 출간 5년 만에 처음으로 개정증보판이 나왔다. 거칠고 부족했던 부분은 부드럽게 매만지고 채워 넣었다. 낡은 이야기는 시대에 맞게 바꿨다. 작가 채사장이 출간 후 많은 독자와 만나 나누었던 이야기들은 책을 개정하는 데 도움이 되었다. 꼼꼼하게 수선된 개정판을 읽는 순간, 왜 지난 5년간 이 책의 아성이 깨지지 않았는지 확인하게 될 것이다.",
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