package com.ssafy.townbook.model.service;

import com.ssafy.townbook.model.dto.response.FindListResponseDto;
import com.ssafy.townbook.model.dto.response.FindOneResponseDto;

public interface BookService {
    
    /**
     * 등록된 전체 도서를 조회
     *
     * @return List<BookDto>
     */
    FindListResponseDto findAllBooks();
    
    /**
     * 도서의 ISBN으로 단일 도서 조회
     *
     * @param bookIsbn
     * @return BookDto
     */
    FindOneResponseDto findBookByBookIsbn(String bookIsbn);
    
    /**
     * 도서의 ISBN으로 국립중앙도서관에서 도서 정보를 가져오고 DB에 등록
     *
     * @param bookIsbn
     * @return BookDto
     */
    FindOneResponseDto findBookInLibraryAndSave(String bookIsbn);
    
    /**
     * 단일 보관함에 보관중인 모든 도서 조회
     *
     * @param lockerNo
     * @return List<BookDto>
     */
    FindListResponseDto findAllBookByLockerNo(Long lockerNo);
}
