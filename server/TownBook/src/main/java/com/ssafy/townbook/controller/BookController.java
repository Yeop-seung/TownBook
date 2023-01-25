package com.ssafy.townbook.controller;

import com.ssafy.townbook.model.entity.Book;
import com.ssafy.townbook.model.repository.BookRepository;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BookController {
    
    private final BookRepository bookRepository;
    
    /**
     * 전체 책 조회
     * ADMIN 권한 확인 후 작동 해야함
     *
     * @return List
     */
    @GetMapping("/book")
//    @PreAuthorize("hasAnyRole('ADMIN')")
    public List<Book> books() {
        return bookRepository.findAll();
    }
    
    /**
     * 도서의 ISBN 으로 도서 조회
     * 권한은 아무나? 일단 보류
     *
     * @param bookIsbn
     * @return Book
     */
    @GetMapping("/book/{bookIsbn}")
    public Book findBookByBookIsbn(@PathVariable String bookIsbn) {
        return bookRepository.findBookByBookIsbn(bookIsbn);
    }
    
    /**
     * 도서 추가
     * V1 : 임의 도서 추가
     * V2 : 바코드로 읽은 ISBN 도서 API 호출
     * V3 : 호출한 도서 추가
     */
    @PostMapping("/book/add")
    public void addBook() {
        Book book = new Book();
        book.setBookIsbn("123");
        book.setBookSubject("0");
        book.setBookTitle("타이틀");
        book.setBookVol(0);
        book.setBookAuthor("오형남");
        book.setBookPublisher("싸피");
        book.setBookPublishPredate(LocalDate.now());
        book.setBookIntroductionURL("URL");
        book.setBookTitleURL("URL");
        bookRepository.save(book);
    }
}
