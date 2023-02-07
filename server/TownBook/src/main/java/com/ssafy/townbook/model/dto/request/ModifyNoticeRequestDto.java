package com.ssafy.townbook.model.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModifyNoticeRequestDto {
    
    private String  noticeTitle;
    private String  noticeContent;
    private Integer noticeCategory;
    
    @Builder
    public ModifyNoticeRequestDto(String noticeTitle, String noticeContent, Integer noticeCategory) {
        this.noticeTitle    = noticeTitle;
        this.noticeContent  = noticeContent;
        this.noticeCategory = noticeCategory;
    }
}