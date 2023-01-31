package com.ssafy.townbook.controller;

import com.ssafy.townbook.model.service.BookLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    
    @GetMapping("")
    public ResponseEntity<?> bookLogs() throws Exception {
        return new ResponseEntity<>(bookLogService.findAll(), HttpStatus.OK);
    }
    
    @GetMapping("/{bookLogNo}")
    public ResponseEntity<?> findBookLogByBookLog(@PathVariable Long bookLogNo) {
        return new ResponseEntity<>(bookLogService.findBookLogByBookLogNo(bookLogNo), HttpStatus.OK);
    }
}
