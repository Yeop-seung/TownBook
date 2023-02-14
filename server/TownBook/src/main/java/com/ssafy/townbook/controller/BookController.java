package com.ssafy.townbook.controller;

import com.ssafy.townbook.model.dto.response.FindListResponseDto;
import com.ssafy.townbook.model.dto.response.FindOneResponseDto;
import com.ssafy.townbook.model.service.BookService;
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
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {
    
    private BookService bookService;
    
    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    
    /**
     * 등록된 전체 도서를 조회
     *
     * @return List<BookDto>
     */
    @GetMapping("")
    public ResponseEntity<FindListResponseDto> findAllBooks() throws Exception {
        return new ResponseEntity<FindListResponseDto>(bookService.findAllBooks(), HttpStatus.OK);
    }
    
    /**
     * 도서의 ISBN으로 단일 도서 조회
     *
     * @param bookIsbn
     * @return BookDto
     */
    @GetMapping("/{bookIsbn}")
    public ResponseEntity<FindOneResponseDto> findBookByBookIsbn(@PathVariable String bookIsbn) {
        return new ResponseEntity<>(bookService.findBookByBookIsbn(bookIsbn), HttpStatus.OK);
    }
    
    /**
     * 도서의 ISBN으로 국립중앙도서관에서 도서 정보를 가져오고 DB에 등록
     *
     * @param bookIsbn
     * @return BookDto
     */
    @PostMapping("/find")
    public ResponseEntity<FindOneResponseDto> findBookInLibraryAndSave(@RequestBody String bookIsbn) {
        return new ResponseEntity<FindOneResponseDto>(bookService.findBookInLibraryAndSave(bookIsbn), HttpStatus.OK);
    }
    
    /**
     * 단일 보관함에 보관중인 모든 도서 조회
     *
     * @param lockerNo
     * @return List<BookDto>
     */
    @GetMapping("/locker/{lockerNo}")
    public ResponseEntity<FindListResponseDto> findAllBookByLockerNo(@PathVariable Long lockerNo) {
        return new ResponseEntity<FindListResponseDto>(bookService.findAllBookByLockerNo(lockerNo), HttpStatus.OK);
    }
}
