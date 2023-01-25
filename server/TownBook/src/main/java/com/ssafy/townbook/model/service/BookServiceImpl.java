package com.ssafy.townbook.model.service;

import com.ssafy.townbook.model.dto.BookDto;
import com.ssafy.townbook.model.entity.Book;
import com.ssafy.townbook.model.repository.BookRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    
    private final BookRepository bookRepository;
    
    public List<BookDto> findAll() {
        return bookRepository.findAll().stream()
                .map(BookDto::new)
                .collect(Collectors.toList());
    }
    
    public BookDto findBookByBookIsbn(String bookIsbn) {
        Book book = bookRepository.findBookByBookIsbn(bookIsbn);
        return new BookDto(book);
    }
}
