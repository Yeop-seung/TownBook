package com.ssafy.townbook.model.dto;

import com.ssafy.townbook.model.entity.Notice;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;


@Getter
@Setter
public class NoticeDto {

    @NotNull
    private String noticeTitle;

    @NotNull
    private String noticeContent;

    @NotNull
    private Integer noticeCategory;

    @NotNull
    private Boolean noticeState;

    @NotNull
    private Integer noticeViews;

    @NotNull
    private Long accountNo;

    @Builder
    public NoticeDto(Notice notice) {
        this.noticeTitle = notice.getNoticeTitle();
        this.noticeContent = notice.getNoticeContent();
        this.noticeCategory = notice.getNoticeCategory();
        this.noticeState = notice.getNoticeState();
        this.noticeViews = notice.getNoticeViews();
        this.accountNo = notice.getAccountNo();
    }
}
