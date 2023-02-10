package com.ssafy.townbook.model.service;

import com.ssafy.townbook.model.dto.request.ModifyNoticeRequestDto;
import com.ssafy.townbook.model.dto.request.WriteNoticeRequestDto;
import com.ssafy.townbook.model.dto.response.FindListResponseDto;
import com.ssafy.townbook.model.dto.response.FindOneResponseDto;
import com.ssafy.townbook.model.dto.response.SaveOneResponseDto;


public interface NoticeService {
    
    FindOneResponseDto findNoticeByNoticeNo(Long noticeNo);
    
    FindListResponseDto findNoticeByNoticeStateAndNoticeCategoryOrderByNoticeNo(Integer category);
    
    SaveOneResponseDto modifyNotice(ModifyNoticeRequestDto modifyNoticeRequestDto);
    
    SaveOneResponseDto writeNotice(WriteNoticeRequestDto writeNoticeRequestDto);
    
    SaveOneResponseDto removeNotice(Long noticeNo);
}
