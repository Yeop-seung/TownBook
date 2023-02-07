package com.ssafy.townbook.controller;

import com.ssafy.townbook.model.dto.BookLogDto;
import com.ssafy.townbook.model.dto.LockerDto;
import com.ssafy.townbook.model.entity.Book;
import com.ssafy.townbook.model.repository.BookRepository;
import com.ssafy.townbook.model.service.BookLogService;
import com.ssafy.townbook.model.service.LockerService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
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
    private LockerService  lockerService;
    private BookRepository bookRepository;
    
    @Autowired
    public SearchController(BookLogService bookLogService, LockerService lockerService, BookRepository bookRepository) {
        this.bookLogService = bookLogService;
        this.lockerService  = lockerService;
        this.bookRepository = bookRepository;
    }
    
    /**
     * 제목 검색해서 북로그 반환
     *
     * @param bookTitle
     * @return List<BookLogDto>
     */
    @GetMapping("/searchTitle/{bookTitle}")
    public ResponseEntity<List<BookLogDto>> findBookLogByBookTitle(@PathVariable String bookTitle) {
        return new ResponseEntity<List<BookLogDto>>(bookLogService.findBookLogByBookTitle(bookTitle), HttpStatus.OK);
    }
    
    @GetMapping("/searchLocker/{lockerNo}")
    public ResponseEntity<JSONObject> findLockerByLockerNo(@PathVariable Long lockerNo) {
        LockerDto        findLockerDto   = lockerService.findLockerByLockerNo(lockerNo);
        List<BookLogDto> findBookLogDtos = bookLogService.findBookLogByLockerNo(lockerNo);
        JSONObject       jsonObject      = new JSONObject();
        
        jsonObject.put("Locker", findLockerDto);
        JSONArray jsonArray = new JSONArray();
        for (BookLogDto bookLogDto : findBookLogDtos) {
            JSONObject jsonObject1 = new JSONObject();
            
            // bookTitle
            Book book = bookRepository.findBookByBookIsbn(bookLogDto.getBookIsbn()).get();
            jsonObject1.put("bookTitle", book.getBookTitle());
            
            jsonObject1.put("detailLockerNo", bookLogDto.getLockerNo());
            jsonObject1.put("detailLockerNoInLocker", bookLogDto.getDetailLockerNo());
            jsonObject1.put("bookIntroductionURL", book.getBookIntroductionURL());
            jsonObject1.put("bookTitleURL", book.getBookTitleURL());
            jsonArray.add(jsonObject1);
        }
        jsonObject.put("BookLogDtos", jsonArray);
        return new ResponseEntity<JSONObject>(jsonObject, HttpStatus.OK);
    }
}
