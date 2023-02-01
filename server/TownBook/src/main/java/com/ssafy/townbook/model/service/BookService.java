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
     * 도서 추가
     * ISBN으로 국립도서관의 도서 정보 불러온 후 DB에 추가
     *
     * @param bookIsbn
     * @return Boolean
     */
    boolean addBook(String bookIsbn);
}
