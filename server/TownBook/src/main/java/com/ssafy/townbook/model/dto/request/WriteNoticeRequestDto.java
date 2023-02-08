package com.ssafy.townbook.model.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WriteNoticeRequestDto {
    
    private String  noticeTitle;
    private String  noticeContent;
    private Integer noticeCategory;
    private Long    accountNo;
    
    
    @Builder
    public WriteNoticeRequestDto(String noticeTitle, String noticeContent, Integer noticeCategory,
            Long accountNo) {
        this.noticeTitle    = noticeTitle;
        this.noticeContent  = noticeContent;
        this.noticeCategory = noticeCategory;
        this.accountNo      = accountNo;
    }
}