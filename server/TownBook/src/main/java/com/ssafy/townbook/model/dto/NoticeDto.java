package com.ssafy.townbook.model.dto;

import com.beust.ah.A;
import com.ssafy.townbook.model.entity.Account;
import com.ssafy.townbook.model.entity.Notice;
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

    @NotNull
    private AdminDto adminDto;

    @Builder
    public NoticeDto(String noticeTitle, String noticeContent, Integer noticeCategory,
            LocalDateTime noticeWriteDateTime, Boolean noticeState, Integer noticeViews,AdminDto adminDto) {
        this.noticeTitle = noticeTitle;
        this.noticeContent = noticeContent;
        this.noticeCategory = noticeCategory;
        this.noticeWriteDateTime = noticeWriteDateTime;
        this.noticeState = noticeState;
        this.noticeViews = noticeViews;
        this.adminDto = adminDto;
    }

    @Builder
    public NoticeDto(Notice notice) {
        this.noticeTitle = notice.getNoticeTitle();
        this.noticeContent = notice.getNoticeContent();
        this.noticeCategory = notice.getNoticeCategory();
        this.noticeState = notice.getNoticeState();
        this.noticeViews = notice.getNoticeViews();
        this.noticeWriteDateTime = notice.getNoticeWriteDateTime();
        this.adminDto = new AdminDto(notice.getAccount());
    }
}
