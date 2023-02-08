package com.ssafy.townbook.controller;

import com.ssafy.townbook.model.dto.request.FindBookInLibraryRequestDto;
import com.ssafy.townbook.model.dto.response.FindOneResponseDto;
import com.ssafy.townbook.model.dto.response.FindListResponseDto;
import com.ssafy.townbook.model.service.BookService;
import com.ssafy.townbook.queryrepository.BookQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {
    
    private BookService bookService;
    
    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    
    /**
     * 전체 책 조회
     *
     * @return List<BookDto>
     */
    @GetMapping("")
    public ResponseEntity<FindListResponseDto> findAllBooks() throws Exception {
        return new ResponseEntity<FindListResponseDto>(bookService.findAllBooks(), HttpStatus.OK);
    }
    
    /**
     * ISBN 으로 도서 조회
     *
     * @param bookIsbn
     * @return BookDto
     */
    @GetMapping("/{bookIsbn}")
    public ResponseEntity<FindOneResponseDto> findBookByBookIsbn(@PathVariable String bookIsbn) {
        return new ResponseEntity<>(bookService.findBookByBookIsbn(bookIsbn), HttpStatus.OK);
    }
    
    /**
     * ISBN으로 국립도서관의 도서 정보 조회
     *
     * @param bookIsbn
     * @return BookDto
     */
    @PostMapping("/find")
    public ResponseEntity<FindOneResponseDto> findBookInLibraryAndSave(@RequestBody String bookIsbn) {
        return new ResponseEntity<FindOneResponseDto>(bookService.findBookInLibraryAndSave(bookIsbn), HttpStatus.OK);
    }
    
    /**
     * 보관함에 보관중이 도서 전부 조회
     *
     * @param lockerNo
     * @return
     */
    @GetMapping("/locker/{lockerNo}")
    public ResponseEntity<FindListResponseDto> findAllBookByLockerNo(@PathVariable Long lockerNo) {
        return new ResponseEntity<FindListResponseDto>(bookService.findAllBookByLockerNo(lockerNo), HttpStatus.OK);
    }
}
