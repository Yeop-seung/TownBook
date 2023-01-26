package com.ssafy.townbook.model.service;

import com.ssafy.townbook.model.dto.BookDto;
import java.util.List;

public interface BookService {
    
    List<BookDto> findAll();
    
    BookDto findBookByBookIsbn(String bookIsbn);
    
    void addBook(String bookIsbn);
}
