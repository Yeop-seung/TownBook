package com.ssafy.townbook.controller;

import com.ssafy.townbook.model.service.BookLogService;
import com.ssafy.townbook.model.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search")
@RequiredArgsConstructor
public class SearchController {
    
    private BookLogService bookLogService;
    private BookService bookService;
    
    @Autowired
    public SearchController(BookLogService bookLogService, BookService bookService) {
        this.bookLogService = bookLogService;
        this.bookService = bookService;
    }
    
    /**
     * 제목 검색해서 북로그 반환
     *
     * @param bookTitle
     * @return List<BookLogDto>
     */
    @GetMapping("/searchTitle/{bookTitle}")
    public ResponseEntity<?> findBookLogByBookTitle(@PathVariable String bookTitle) {
        return new ResponseEntity<>(bookLogService.findBookLogByBookTitle(bookTitle), HttpStatus.OK);
    }
//    @GetMapping("/searchTitle")
//    public ResponseEntity<?> findLockerByBookTitleAndDist(SearchTitleRequestDto searchTitleRequestDto)
//            throws Exception {
//        LockerDto lockerDto = bookLogService.findLockerByBookTitleAndDist(searchTitleRequestDto);
//        return null;
//    }
}
