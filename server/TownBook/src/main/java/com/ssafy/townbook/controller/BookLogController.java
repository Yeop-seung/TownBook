package hide information.townbook.controller;

import hide information.townbook.model.dto.request.DonateBookRequestDto;
import hide information.townbook.model.dto.request.ReceiveBookRequestDto;
import hide information.townbook.model.dto.request.ReceiverWishListRequestDto;
import hide information.townbook.model.dto.response.FindListResponseDto;
import hide information.townbook.model.dto.response.FindOneResponseDto;
import hide information.townbook.model.service.BookLogService;
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
    public ResponseEntity<FindListResponseDto> findAllBookLogs() {
        return new ResponseEntity<FindListResponseDto>(bookLogService.findAllBookLogs(), HttpStatus.OK);
    }
    
    /**
     * 단일 북로그 조회
     *
     * @param bookLogNo
     * @return BookLogDto
     */
    @GetMapping("/{bookLogNo}")
    public ResponseEntity<FindOneResponseDto> findBookLogByBookLogNo(@PathVariable Long bookLogNo) {
        return new ResponseEntity<FindOneResponseDto>(bookLogService.findBookLogByBookLogNo(bookLogNo), HttpStatus.OK);
    }
    
    /**
     * 단일 회원의 모든 북로그 조회
     *
     * @param accountNo
     * @return List<BookLogDto>
     */
    @GetMapping("/account/{accountNo}")
    public ResponseEntity<FindListResponseDto> findBookLogByAccountNo(@PathVariable Long accountNo) {
        return new ResponseEntity<FindListResponseDto>(bookLogService.findBookLogByAccountNo(accountNo), HttpStatus.OK);
    }
    
    /**
     * 단일 보관함에 보관중인 도서 전부 조회
     *
     * @param lockerNo
     * @return List<BookDto>
     */
    @GetMapping("/locker/{lockerNo}")
    public ResponseEntity<FindListResponseDto> findBookLogByLockerNo(@PathVariable Long lockerNo) {
        return new ResponseEntity<FindListResponseDto>(bookLogService.findBookLogByLockerNo(lockerNo), HttpStatus.OK);
    }
    
    /**
     * 단일 도서의 모든 리뷰 조회
     *
     * @param bookIsbn
     * @return List<BookLogDto.bookLogReview>
     */
    @GetMapping("/review/{bookIsbn}")
    public ResponseEntity<FindListResponseDto> findBookLogReviewByBookIsbn(@PathVariable String bookIsbn) {
        return new ResponseEntity<FindListResponseDto>(bookLogService.findBookLogReviewByBookIsbn(bookIsbn),
                HttpStatus.OK);
    }
    
    /**
     * 도서 기부
     *
     * @param donateBookRequestDto
     * @return AdminDto
     */
    @PostMapping("/donateBook")
    public ResponseEntity<FindOneResponseDto> donateBook(@RequestBody DonateBookRequestDto donateBookRequestDto)
            throws Exception {
        return new ResponseEntity<FindOneResponseDto>(bookLogService.donateBook(donateBookRequestDto),
                HttpStatus.OK);
    }
    
    /**
     * 도서 수령
     *
     * @param receiveBookRequestDto
     * @return Boolean
     * @throws Exception
     */
    @PostMapping("/receiveBook")
    public ResponseEntity<FindOneResponseDto> receiveBook(
            @RequestBody ReceiveBookRequestDto receiveBookRequestDto) throws Exception {
        return new ResponseEntity<FindOneResponseDto>(bookLogService.receiveBook(receiveBookRequestDto),
                HttpStatus.OK);
    }
    /**
     * 책 찜 상태 변경
     *
     * @param receiverWishListRequestDto
     * @return Boolean
     * @throws Exception
     */
    @PostMapping("/wishList")
    public ResponseEntity<FindOneResponseDto> changeWishState(@RequestBody ReceiverWishListRequestDto receiverWishListRequestDto)throws Exception{
        return new ResponseEntity<FindOneResponseDto>(bookLogService.changeWishState(receiverWishListRequestDto),HttpStatus.OK);
    }
}
