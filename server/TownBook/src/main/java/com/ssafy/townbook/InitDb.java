package com.ssafy.townbook;

import com.ssafy.townbook.model.entity.Account;
import com.ssafy.townbook.model.entity.Book;
import java.time.LocalDate;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * 프로젝트 실행하면 책 DB에 입력
 */
@Component
@RequiredArgsConstructor
public class InitDb {
    
    private final InitService initService;
    
    @PostConstruct
    public void init() {
        initService.bookInit();
        initService.accountInit();
    }
    
    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        
        private final EntityManager em;
        
        public void bookInit() {
            Book book1 = createBook("8984993751", "8", "토지", "박경리", "커뮤니케이션 북스", LocalDate.now(),
                    null, null);
            em.persist(book1);
            
            Book book2 = createBook("9788960777330", "0",
                    "자바 ORM 표준 JPA 프로그래밍(스프링 데이터 예제 프로젝트로 배우는 전자정부 표준 데이터베이스 프레임워크)",
                    "김영한", "에이콘출판", LocalDate.now(),
                    "https://www.nl.go.kr/seoji/fu/ecip/dbfiles/CIP_FILES_TBL/4721454_5.txt"
                    , "https://www.nl.go.kr/seoji/fu/ecip/dbfiles/CIP_FILES_TBL/4721454_3.jpg");
            em.persist(book2);
        }
        
        private Book createBook(String bookIsbn, String bookSubject, String bookTitle, String bookAuthor,
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
            Account account1 = createAccount("test@test.com", "password", "김싸피", "대전시 유성구 덕명동", "010-1234-5678", 0,
                    "내가 바로 김싸피", "220222");
            em.persist(account1);
            
            Account account2 = createAccount("admin@test.com", "adminPassword", "최어드", "대전시 유성구 어드동", "010-5678-1234", 1,
                    "내가 바로 최어드", "111111");
            em.persist(account2);
        }
        
        private Account createAccount(String accountEmail, String accountPw, String accountName, String accountAddress,
                String accountPhoneNumber, Integer accountGender, String accountNickname, String accountBirthDay) {
            Account account = new Account();
            account.setAccountEmail(accountEmail);
            account.setAccountPw(accountPw);
            account.setAccountName(accountName);
            account.setAccountAddress(accountAddress);
            account.setAccountPhoneNumber(accountPhoneNumber);
            account.setAccountGender(accountGender);
            account.setAccountNickname(accountNickname);
            account.setAccountBirthday(accountBirthDay);
            return account;
        }
    }
}
