package com.ssafy.townbook;

import com.ssafy.townbook.model.entity.Book;
import com.ssafy.townbook.model.entity.DetailLocker;
import com.ssafy.townbook.model.entity.Locker;
import java.time.LocalDate;
import java.util.List;
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
        initService.dbInit();
    }
    
    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        
        private final EntityManager em;
        
        public void dbInit() {
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
    }
}
