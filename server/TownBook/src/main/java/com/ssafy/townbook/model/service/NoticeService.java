package com.ssafy.townbook.model.service;

import com.ssafy.townbook.model.dto.NoticeDto;
import com.ssafy.townbook.model.entity.Notice;
import java.util.List;


public interface NoticeService {
    Boolean modifyNotice(Notice notice);

    Boolean writeNotice(Notice notice);

    List<NoticeDto> getNoticeList(Integer category);

    NoticeDto getNotice(Long noticeNo);

    Boolean removeNotice(Long noticeNo);
}
