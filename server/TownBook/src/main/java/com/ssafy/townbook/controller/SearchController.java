package com.ssafy.townbook.controller;

import com.ssafy.townbook.model.dto.LockerDto;
import com.ssafy.townbook.model.dto.response.ReceiveBookLogResponseDto;
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
    
    @Autowired
    public SearchController(BookLogService bookLogService, LockerService lockerService) {
        this.bookLogService = bookLogService;
        this.lockerService  = lockerService;
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
    
    @GetMapping("/searchLocker/{lockerNo}")
    public ResponseEntity<?> findLockerByLockerNo(@PathVariable Long lockerNo) {
        LockerDto                       findLockerDto                  = lockerService.findLockerByLockerNo(lockerNo);
        List<ReceiveBookLogResponseDto> findReceiveBookLogResponseDtos = bookLogService.findBookLogByLockerNo(lockerNo);
        
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Locker", findLockerDto);
        
        JSONArray jsonArray = new JSONArray();
        for (ReceiveBookLogResponseDto bookLogDto : findReceiveBookLogResponseDtos) {
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("bookTitle", bookLogDto.getBookTitle());
            jsonObject1.put("detailLockerNo", bookLogDto.getDetailLockerNo());
            jsonObject1.put("detailLockerNoInLocker", bookLogDto.getDetailLockerNoInLocker());
            jsonObject1.put("bookIntroductionURL", bookLogDto.getBookIntroductionURL());
            jsonObject1.put("bookTitleURL", bookLogDto.getBookTitleURL());
            jsonArray.add(jsonObject1);
        }
        jsonObject.put("BookLogDtos", jsonArray);
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }
    //    @GetMapping("/searchTitle")
    //    public ResponseEntity<?> findLockerByBookTitleAndDist(SearchTitleRequestDto searchTitleRequestDto)
    //            throws Exception {
    //        LockerDto lockerDto = bookLogService.findLockerByBookTitleAndDist(searchTitleRequestDto);
    //        return null;
    //    }
}
