package com.ssafy.townbook.model.service;

import com.ssafy.townbook.model.dto.request.DonateBookRequestDto;
import com.ssafy.townbook.model.dto.request.ReceiveBookRequestDto;
import com.ssafy.townbook.model.dto.response.FindListResponseDto;
import com.ssafy.townbook.model.dto.response.FindOneResponseDto;

public interface BookLogService {
    
    /**
     * 전체 북로그 조회
     *
     * @return List<BookLogDto>
     */
    FindListResponseDto findAllBookLogs();
    
    /**
     * 단일 북로그 조회
     *
     * @param bookLogNo
     * @return BookLogDto
     */
    FindOneResponseDto findBookLogByBookLogNo(Long bookLogNo);
    
    /**
     * 단일 회원의 모든 북로그 조회
     *
     * @param accountNo
     * @return List<BookLog>
     */
    FindListResponseDto findBookLogByAccountNo(Long accountNo);
    
    /**
     * 단일 보관함에 보관중인 도서 전부 조회
     *
     * @param lockerNo
     * @return List<BookDto>
     */
    FindListResponseDto findBookLogByLockerNo(Long lockerNo);
    
    /**
     * 단일 도서의 모든 리뷰 조회
     *
     * @param bookIsbn
     * @return List<BookLogDto.bookLogReview>
     */
    FindListResponseDto findBookLogReviewByBookIsbn(String bookIsbn);
    
    
    /**
     * 도서 기부
     *
     * @param donateBookRequestDto
     * @return AccountDto
     */
    FindOneResponseDto donateBook(DonateBookRequestDto donateBookRequestDto) throws Exception;
    
    /**
     * 도서 수령
     *
     * @param receiveBookRequestDto
     * @return Boolean
     * @throws Exception
     */
    FindOneResponseDto receiveBook(ReceiveBookRequestDto receiveBookRequestDto) throws Exception;
}
