package com.ssafy.townbook.controller;

import com.ssafy.townbook.model.dto.request.ModifyNoticeRequestDto;
import com.ssafy.townbook.model.dto.request.WriteNoticeRequestDto;
import com.ssafy.townbook.model.dto.response.FindListResponseDto;
import com.ssafy.townbook.model.dto.response.FindOneResponseDto;
import com.ssafy.townbook.model.dto.response.SaveOneResponseDto;
import com.ssafy.townbook.model.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notice")
@Transactional(readOnly = true)
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
    public ResponseEntity<FindOneResponseDto> getNotice(@PathVariable Long noticeNo) {
        return new ResponseEntity<FindOneResponseDto>(noticeService.getNotice(noticeNo), HttpStatus.OK);
    }
    
    
    /**
     * 카테고리(공지사항, 이용안내) 별로 최신 8개 리스트 가져오기
     *
     * @param category
     * @return List<NoticeDto>
     */
    @GetMapping("/list/{category}")
    public ResponseEntity<FindListResponseDto> findByNoticeStateAndNoticeCategoryOrderByNoticeNo(
            @PathVariable Integer category) {
        return new ResponseEntity<FindListResponseDto>(
                noticeService.findByNoticeStateAndNoticeCategoryOrderByNoticeNo(category),
                HttpStatus.OK);
    }
    
    /**
     * 공지사항/이용안내 수정
     *
     * @param modifyNoticeRequestDto
     * @return Boolean
     */
    @Transactional
    @PutMapping("/modify")
    public ResponseEntity<SaveOneResponseDto> modifyNotice(@RequestBody ModifyNoticeRequestDto modifyNoticeRequestDto) {
        return new ResponseEntity<SaveOneResponseDto>(noticeService.modifyNotice(modifyNoticeRequestDto),
                HttpStatus.OK);
    }
    
    /**
     * 공지사항/이용안내 작성
     *
     * @param writeNoticeRequestDto
     * @return Boolean
     */
    @Transactional
    @PostMapping("/write")
    public ResponseEntity<Boolean> writeNotice(@RequestBody WriteNoticeRequestDto writeNoticeRequestDto) {
        System.out.println("writeNoticeRequestDto = " + writeNoticeRequestDto);
        Boolean check = noticeService.writeNotice(writeNoticeRequestDto);
        System.out.println("check = " + check);
        return new ResponseEntity<Boolean>(check, HttpStatus.OK);
    }
    
    /**
     * 공지사항/이용안내 삭제
     *
     * @param noticeNo
     * @return Boolean
     */
    @Transactional
    @PutMapping("/remove")
    public ResponseEntity<Boolean> removeNotice(Long noticeNo) {
        return new ResponseEntity<Boolean>(noticeService.removeNotice(noticeNo), HttpStatus.OK);
    }
}
