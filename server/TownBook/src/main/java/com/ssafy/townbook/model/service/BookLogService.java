package com.ssafy.townbook.model.service;

import com.ssafy.townbook.model.dto.BookLogDto;
import com.ssafy.townbook.model.dto.request.DonateBookRequestDto;
import com.ssafy.townbook.model.dto.request.ReceiveBookRequestDto;
import com.ssafy.townbook.model.dto.response.DonateBookLogResponseDto;
import com.ssafy.townbook.model.dto.response.ReceiveBookLogResponseDto;
import java.util.List;

public interface BookLogService {
    
    /**
     * 전체 북로그 조회
     *
     * @return List<BookLogDto>
     */
    List<BookLogDto> findAll();
    
    /**
     * 단일 북로그 조회
     *
     * @param bookLogNo
     * @return BookLogDto
     */
    BookLogDto findBookLogByBookLogNo(Long bookLogNo);
    
    /**
     * 단일 보관함에 보관중인 도서 전부 조회
     *
     * @param lockerNo
     * @return List<BookDto>
     */
    List<BookLogDto> findBookLogByLockerNo(Long lockerNo);
    
    /**
     * 단일 도서의 모든 리뷰 조회
     *
     * @param bookIsbn
     * @return List<BookLogDto.bookLogReview>
     */
    List<String> findBookLogReviewByBookIsbn(String bookIsbn);
    
    /**
     * 단일 회원의 모든 북로그 조회
     *
     * @param accountNo
     * @return List<BookLog>
     */
    List<BookLogDto> findBookLogByAccountNo(Long accountNo);
    
    /**
     * 도서 기부
     *
     * @param donateBookRequestDto
     * @return AccountDto
     */
    DonateBookLogResponseDto donateBook(DonateBookRequestDto donateBookRequestDto) throws Exception;
    
    /**
     * 도서 수령
     *
     * @param receiveBookRequestDto
     * @return Boolean
     * @throws Exception
     */
    ReceiveBookLogResponseDto receiveBook(ReceiveBookRequestDto receiveBookRequestDto) throws Exception;
    
    /**
     * 제목 검색해서 북로그 반환
     *
     * @param bookTitle
     * @return List<BookLogDto>
     */
    List<BookLogDto> findBookLogByBookTitle(String bookTitle);
}
