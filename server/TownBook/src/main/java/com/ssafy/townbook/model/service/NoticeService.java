package hide information.townbook.model.service;

import hide information.townbook.model.dto.request.ModifyNoticeRequestDto;
import hide information.townbook.model.dto.request.WriteNoticeRequestDto;
import hide information.townbook.model.dto.response.FindListResponseDto;
import hide information.townbook.model.dto.response.FindOneResponseDto;
import hide information.townbook.model.dto.response.SaveOneResponseDto;


public interface NoticeService {
    
    FindOneResponseDto findNoticeByNoticeNo(Long noticeNo);
    
    FindListResponseDto findNoticeByNoticeStateAndNoticeCategoryOrderByNoticeNo(Integer category);
    
    SaveOneResponseDto modifyNotice(ModifyNoticeRequestDto modifyNoticeRequestDto);
    
    SaveOneResponseDto writeNotice(WriteNoticeRequestDto writeNoticeRequestDto);
    
    SaveOneResponseDto removeNotice(Long noticeNo);
    
    /**
     * 공지사항/이용안내 전체 조회
     *
     * @return List<NoticeDto>
     */
    FindListResponseDto findAllNotice();
}
