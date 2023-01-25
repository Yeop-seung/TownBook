package com.ssafy.townbook.controller;

import com.ssafy.townbook.model.entity.Book;
import com.ssafy.townbook.model.repository.BookRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BookController {
    
    private final BookRepository bookRepository;
    
    @GetMapping("/book")
    public List<Book> books() {
        return bookRepository.findAll();
    }
    
    @GetMapping("/book/{bookIsbn}")
    public Book findBookByBookIsbn(@PathVariable String bookIsbn) {
        return bookRepository.findBookByBookIsbn(bookIsbn);
    }
}
