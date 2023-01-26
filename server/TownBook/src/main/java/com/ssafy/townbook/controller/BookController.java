package com.ssafy.townbook.controller;

import com.ssafy.townbook.model.dto.BookDto;
import com.ssafy.townbook.model.service.BookService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/book")
@RequiredArgsConstructor
public class BookController {
    
    private BookService bookService;
    
    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    
    /**
     * 전체 책 조회
     * ADMIN 권한 확인 후 작동 해야함
     *
     * @return List
     */
    @GetMapping("")
//    @PreAuthorize("hasAnyRole('ADMIN')")
    public List<BookDto> books() {
        return bookService.findAll();
    }
    
    /**
     * 도서의 ISBN 으로 도서 조회
     * 권한은 아무나? 일단 보류
     *
     * @param bookIsbn
     * @return Book
     */
    @GetMapping("/{bookIsbn}")
    public BookDto findBookByBookIsbn(@PathVariable String bookIsbn) {
        return bookService.findBookByBookIsbn(bookIsbn);
    }
    
    /**
     * 도서 추가
     * ISBN으로 국립도서관의 도서 정보 불러온 후 DB에 추가
     */
    @PostMapping("/add/{bookIsbn}")
    public void add(@PathVariable String bookIsbn) {
        bookService.addBook(bookIsbn);
    }
}
