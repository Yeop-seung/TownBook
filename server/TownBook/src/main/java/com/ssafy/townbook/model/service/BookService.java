package com.ssafy.townbook.model.service;

import com.ssafy.townbook.model.dto.response.FindOneResponseDto;
import com.ssafy.townbook.model.dto.response.FindListResponseDto;

public interface BookService {
    
    /**
     * 전체 책 조회
     *
     * @return List<BookDto>
     */
    FindListResponseDto findAllBooks();
    
    /**
     * ISBN 으로 도서 조회
     *
     * @param bookIsbn
     * @return BookDto
     */
    FindOneResponseDto findBookByBookIsbn(String bookIsbn);
    
    /**
     * ISBN으로 국립도서관의 도서 정보 조회
     *
     * @param bookIsbn
     * @return BookDto
     */
    FindOneResponseDto findBookInLibraryAndSave(String bookIsbn);
}
