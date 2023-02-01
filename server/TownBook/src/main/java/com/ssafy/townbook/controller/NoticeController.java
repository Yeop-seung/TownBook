package com.ssafy.townbook.controller;

import com.ssafy.townbook.model.dto.NoticeDto;
import com.ssafy.townbook.model.entity.Notice;
import com.ssafy.townbook.model.service.NoticeService;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController("/notice")
public class NoticeController {

    @Autowired
    NoticeService noticeService;

    /**
     * 공지사항 하나 가져오기
     *
     * @param noticeNo
     * @return noticeDto
     */
    @GetMapping("/{noticeNo}")
    public ResponseEntity<?> getNotice(@PathVariable Long noticeNo){
        return new ResponseEntity(noticeService.getNotice(noticeNo), HttpStatus.OK);
    }

    /**
     * 공지사항 수정
     *
     * @param notice
     * @return Boolean
     */
    @PutMapping("/modify")
    public ResponseEntity modifyNotice(Notice notice){
        return new ResponseEntity(noticeService.modifyNotice(notice), HttpStatus.OK);
    }

    /**
     *
     * @param notice
     * @return Boolean
     */
    @PostMapping("/write")
    public ResponseEntity writeNotice(Notice notice){
        return new ResponseEntity(noticeService.writeNotice(notice), HttpStatus.OK);
    }

    /**
     * 카테고리(공지사항, 이용안내) 별로 최신 8개 리스트 가져오기
     *
     * @param category
     * @return List<NoticeDto>
     */
    @GetMapping("/list/{category}")
    public ResponseEntity<?> getNoticeList(@PathVariable Integer category){
        return new ResponseEntity(noticeService.getNoticeList(category), HttpStatus.OK);
    }

    /**
     *공지사항 삭제
     *
     * @param noticeNo
     * @return Boolean
     */
    @PutMapping("/remove")
    public ResponseEntity removeNotice(Long noticeNo){
        return new ResponseEntity(noticeService.removeNotice(noticeNo), HttpStatus.OK);
    }
}
