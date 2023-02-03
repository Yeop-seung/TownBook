package com.ssafy.townbook.model.service;

import com.ssafy.townbook.model.dto.BookDto;
import java.util.List;

public interface BookService {
    
    /**
     * 전체 책 조회
     *
     * @return List<BookDto>
     */
    List<BookDto> findAll();
    
    /**
     * ISBN 으로 도서 조회
     *
     * @param bookIsbn
     * @return BookDto
     */
    BookDto findBookByBookIsbn(String bookIsbn);
    
    /**
     * ISBN으로 국립도서관의 도서 정보 조회
     *
     * @param bookIsbn
     * @return BookDto
     */
    BookDto addBook(String bookIsbn);
}
