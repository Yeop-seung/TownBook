package com.ssafy.townbook.controller;

import com.ssafy.townbook.model.dto.NoticeDto;
import com.ssafy.townbook.model.dto.request.ModifyNoticeRequestDto;
import com.ssafy.townbook.model.dto.request.WriteNoticeRequestDto;
import com.ssafy.townbook.model.service.NoticeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notice")
public class NoticeController {
    
    private NoticeService noticeService;
    
    @Autowired
    public NoticeController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }
    
    /**
     * 공지사항 하나 가져오기
     *
     * @param noticeNo
     * @return noticeDto
     */
    @GetMapping("/{noticeNo}")
    public ResponseEntity<NoticeDto> getNotice(@PathVariable Long noticeNo) {
        return new ResponseEntity<NoticeDto>(noticeService.getNotice(noticeNo), HttpStatus.OK);
    }
    
    /**
     * 공지사항 수정
     *
     * @param modifyNoticeRequestDto
     * @return Boolean
     */
    @PutMapping("/modify")
    public ResponseEntity<Boolean> modifyNotice(ModifyNoticeRequestDto modifyNoticeRequestDto){
        return new ResponseEntity<Boolean>(noticeService.modifyNotice(modifyNoticeRequestDto), HttpStatus.OK);
    }
    
    /**
     * @param writeNoticeRequestDto
     * @return Boolean
     */
    @PostMapping("/write")
    public ResponseEntity<Boolean> writeNotice(WriteNoticeRequestDto writeNoticeRequestDto)  throws Exception{
        return new ResponseEntity<Boolean>(noticeService.writeNotice(writeNoticeRequestDto), HttpStatus.OK);
    }
    
    /**
     * 카테고리(공지사항, 이용안내) 별로 최신 8개 리스트 가져오기
     *
     * @param category
     * @return List<NoticeDto>
     */
    @GetMapping("/list/{category}")
    public ResponseEntity<List<NoticeDto>> findTop8ByNoticeStateAndNoticeCategoryOrderByNoticeNo(
            @PathVariable Integer category) {
        return new ResponseEntity<List<NoticeDto>>(
                noticeService.findTop8ByNoticeStateAndNoticeCategoryOrderByNoticeNo(category),
                HttpStatus.OK);
    }
    
    /**
     * 공지사항 삭제
     *
     * @param noticeNo
     * @return Boolean
     */
    @PutMapping("/remove")
    public ResponseEntity<Boolean> removeNotice(Long noticeNo) {
        return new ResponseEntity<Boolean>(noticeService.removeNotice(noticeNo), HttpStatus.OK);
    }
}
