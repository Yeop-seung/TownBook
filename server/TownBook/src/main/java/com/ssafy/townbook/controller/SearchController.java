package com.ssafy.townbook.controller;

import com.ssafy.townbook.model.dto.response.FindListResponseDto;
import com.ssafy.townbook.model.service.SearchService;
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
    
    private SearchService searchService;
    
    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }
    
    /**
     * 제목 검색해서 북로그 반환
     *
     * @param bookTitle
     * @return List<BookLogDto>
     */
    @GetMapping("/searchTitle/{bookTitle}")
    public ResponseEntity<FindListResponseDto> searchBookLogByBookTitle(@PathVariable String bookTitle) {
        return new ResponseEntity<FindListResponseDto>(searchService.searchBookLogByBookTitle(bookTitle), HttpStatus.OK);
    }
    
    /**
     * 보관함 검색
     *
     * @param lockerNo
     * @return JSONObject
     */
    @GetMapping("/searchLocker/{lockerNo}")
    public ResponseEntity<FindListResponseDto> searchLockerByLockerNo(@PathVariable Long lockerNo) {
        return new ResponseEntity<FindListResponseDto>(searchService.searchLockerByLockerNo(lockerNo), HttpStatus.OK);
    }
}
