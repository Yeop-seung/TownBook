package com.ssafy.townbook.model.dto.request;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WriteNoticeRequestDto {
    
    private String        noticeTitle;
    private String        noticeContent;
    private Integer       noticeCategory;
    private LocalDateTime noticeWriteDateTime;
    private Long          accountNo;
    
    
    @Builder
    public WriteNoticeRequestDto(String noticeTitle, String noticeContent, Integer noticeCategory,
                                 LocalDateTime noticeWriteDateTime, Long accountNo) {
        this.noticeTitle         = noticeTitle;
        this.noticeContent       = noticeContent;
        this.noticeCategory      = noticeCategory;
        this.noticeWriteDateTime = noticeWriteDateTime;
        this.accountNo           = accountNo;
    }
}