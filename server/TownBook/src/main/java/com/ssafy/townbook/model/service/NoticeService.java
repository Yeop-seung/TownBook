package com.ssafy.townbook.model.service;

import com.ssafy.townbook.model.dto.NoticeDto;
import com.ssafy.townbook.model.dto.request.ModifyNoticeRequestDto;
import com.ssafy.townbook.model.dto.request.WriteNoticeRequestDto;
import java.util.List;


public interface NoticeService {
    
    Boolean modifyNotice(ModifyNoticeRequestDto modifyNoticeRequestDto);
    
    Boolean writeNotice(WriteNoticeRequestDto writeNoticeRequestDto);
    
    List<NoticeDto> findTop8ByNoticeStateAndNoticeCategoryOrderByNoticeNo(Integer category);
    
    NoticeDto getNotice(Long noticeNo);
    
    Boolean removeNotice(Long noticeNo);
}
