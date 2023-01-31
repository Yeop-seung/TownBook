package com.ssafy.townbook.controller;

import com.ssafy.townbook.model.entity.Notice;
import com.ssafy.townbook.model.service.NoticeService;
import java.util.List;
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

    @GetMapping("/{noticeNo}")
    public ResponseEntity<Notice> getNotice(@PathVariable Long noticeNo){
        return new ResponseEntity<Notice>(noticeService.getNotice(noticeNo), HttpStatus.OK);

    }

    @PutMapping("/modify")
    public ResponseEntity modifyNotice(Notice notice){
        return new ResponseEntity(noticeService.modifyNotice(notice), HttpStatus.OK);
    }

    @PostMapping("/write")
    public ResponseEntity writeNotice(Notice notice){
        return new ResponseEntity(noticeService.writeNotice(notice), HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Notice>> getNoticeList(){
        return new ResponseEntity<List<Notice>>(noticeService.getNoticeList(), HttpStatus.OK);
    }

    @PutMapping("/remove")
    public ResponseEntity removeNotice(Long noticeNo){
        return new ResponseEntity(noticeService.removeNotice(noticeNo), HttpStatus.OK);
    }
}
