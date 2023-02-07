package com.ssafy.townbook.controller;

import com.ssafy.townbook.model.dto.BookLogDto;
import com.ssafy.townbook.model.dto.request.DonateBookRequestDto;
import com.ssafy.townbook.model.dto.request.ReceiveBookRequestDto;
import com.ssafy.townbook.model.dto.response.DonateBookLogResponseDto;
import com.ssafy.townbook.model.dto.response.ReceiveBookLogResponseDto;
import com.ssafy.townbook.model.entity.BookLog;
import com.ssafy.townbook.model.service.BookLogService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bookLog")
@RequiredArgsConstructor
public class BookLogController {
    
    private BookLogService bookLogService;
    
    @Autowired
    public BookLogController(BookLogService bookLogService) {
        this.bookLogService = bookLogService;
    }
    
    /**
     * 전체 북로그 조회
     *
     * @return List<BookLogDto>
     */
    @GetMapping("")
    public ResponseEntity<List<BookLogDto>> bookLogs() {
        return new ResponseEntity<List<BookLogDto>>(bookLogService.findAll(), HttpStatus.OK);
    }
    
    /**
     * 단일 북로그 조회
     *
     * @param bookLogNo
     * @return BookLogDto
     */
    @GetMapping("/{bookLogNo}")
    public ResponseEntity<BookLogDto> findBookLogByBookLog(@PathVariable Long bookLogNo) {
        return new ResponseEntity<BookLogDto>(bookLogService.findBookLogByBookLogNo(bookLogNo), HttpStatus.OK);
    }
    
    /**
     * 단일 회원의 모든 북로그 조회
     *
     * @param accountNo
     * @return List<BookLog>
     */
    @GetMapping("/account/{accountNo}")
    public ResponseEntity<List<BookLogDto>> findBookLogByAccountNo(@PathVariable Long accountNo) {
        return new ResponseEntity<List<BookLogDto>>(bookLogService.findBookLogByAccountNo(accountNo), HttpStatus.OK);
    }
    
    /**
     * 단일 보관함에 보관중인 도서 전부 조회
     *
     * @param lockerNo
     * @return List<BookDto>
     */
    @GetMapping("/locker/{lockerNo}")
    public ResponseEntity<List<BookLogDto>> findBookLogByLockerNo(@PathVariable Long lockerNo) {
        return new ResponseEntity<List<BookLogDto>>(bookLogService.findBookLogByLockerNo(lockerNo), HttpStatus.OK);
    }
    
    /**
     * 단일 도서의 모든 리뷰 조회
     *
     * @param bookIsbn
     * @return List<BookLogDto.bookLogReview>
     */
    @GetMapping("/review/{bookIsbn}")
    public ResponseEntity<List<String>> findBookLogReviewByBookIsbn(@PathVariable String bookIsbn) {
        return new ResponseEntity<List<String>>(bookLogService.findBookLogReviewByBookIsbn(bookIsbn), HttpStatus.OK);
    }
    
    /**
     * 도서 기부
     *
     * @param donateBookRequestDto
     * @return AdminDto
     */
    @PostMapping("/donateBook")
    public ResponseEntity<DonateBookLogResponseDto> donateBook(@RequestBody DonateBookRequestDto donateBookRequestDto) throws Exception {
        return new ResponseEntity<DonateBookLogResponseDto>(bookLogService.donateBook(donateBookRequestDto), HttpStatus.OK);
    }
    
    /**
     * 도서 수령
     *
     * @param receiveBookRequestDto
     * @return Boolean
     * @throws Exception
     */
    @PostMapping("/receiveBook")
    public ResponseEntity<ReceiveBookLogResponseDto> receiveBook(@RequestBody ReceiveBookRequestDto receiveBookRequestDto) throws Exception {
        return new ResponseEntity<ReceiveBookLogResponseDto>(bookLogService.receiveBook(receiveBookRequestDto), HttpStatus.OK);
    }
}
