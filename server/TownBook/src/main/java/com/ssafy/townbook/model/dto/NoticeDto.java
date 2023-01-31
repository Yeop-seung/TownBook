package com.ssafy.townbook.model.dto;

import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class NoticeDto {

    @NotNull
    private String noticeTitle;

    @NotNull
    private String noticeContent;

    @NotNull
    private Integer noticeCategory;

    @NotNull
    private LocalDateTime noticeWriteDateTime;

    @NotNull
    private Boolean noticeState;

    @NotNull
    private Integer noticeViews;

    private AccountDto accountDto;

    @Builder
    public NoticeDto(String noticeTitle, String noticeContent, Integer noticeCategory,
            LocalDateTime noticeWriteDateTime, Boolean noticeState, Integer noticeViews) {
        this.noticeTitle = noticeTitle;
        this.noticeContent = noticeContent;
        this.noticeCategory = noticeCategory;
        this.noticeWriteDateTime = noticeWriteDateTime;
        this.noticeState = noticeState;
        this.noticeViews = noticeViews;
    }
}
