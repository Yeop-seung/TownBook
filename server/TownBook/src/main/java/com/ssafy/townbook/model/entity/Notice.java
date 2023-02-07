package com.ssafy.townbook.model.entity;

import com.ssafy.townbook.model.dto.request.ModifyNoticeRequestDto;
import com.ssafy.townbook.model.dto.request.WriteNoticeRequestDto;
import java.time.LocalDateTime;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;


@DynamicInsert
@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "`notice`")
public class Notice {
    
    @Id
    @Column(name = "notice_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long noticeNo;
    
    @NotNull
    @Column(name = "notice_title")
    private String noticeTitle;
    
    @NotNull
    @Column(name = "notice_content")
    @Lob
    private String noticeContent;
    
    @NotNull
    @Column(name = "notice_category")
    private Integer noticeCategory;

    @Column(name = "notice_write_time")
    private LocalDateTime noticeWriteDateTime;
    
    @Column(name = "notice_state")
    @ColumnDefault("true")
    private Boolean noticeState;
    
    @Column(name = "notice_views")
    @ColumnDefault("0")
    private Integer noticeViews;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "`fk-account-notice`", insertable = false, updatable = false)
    private Account account;
    
    @Column(name = "`fk-account-notice`")
    private Long accountNo;
    
    
    @Builder
    public Notice(Long accountNo) {
        this.accountNo = accountNo;
    }
    
    @Builder
    public Notice(WriteNoticeRequestDto writeNoticeRequestDto) {
        this.noticeTitle         = writeNoticeRequestDto.getNoticeTitle();
        this.noticeContent       = writeNoticeRequestDto.getNoticeContent();
        this.noticeWriteDateTime = writeNoticeRequestDto.getNoticeWriteDateTime();
        this.noticeCategory      = writeNoticeRequestDto.getNoticeCategory();
        this.accountNo           = writeNoticeRequestDto.getAccountNo();
    }
    
    @Builder
    public Notice(ModifyNoticeRequestDto modifyNoticeRequestDto) {
        this.noticeTitle    = modifyNoticeRequestDto.getNoticeTitle();
        this.noticeContent  = modifyNoticeRequestDto.getNoticeContent();
        this.noticeCategory = modifyNoticeRequestDto.getNoticeCategory();
    }
    
    @Builder
    public Notice(Long noticeNo, String noticeTitle, String noticeContent,
                  LocalDateTime noticeWriteDateTime, Boolean noticeState, Integer noticeViews,
                  Integer noticeCategory,
                  Long accountNo) {
        this.noticeNo            = noticeNo;
        this.noticeTitle         = noticeTitle;
        this.noticeContent       = noticeContent;
        this.noticeWriteDateTime = noticeWriteDateTime;
        this.noticeCategory      = noticeCategory;
        this.noticeState         = noticeState;
        this.noticeViews         = noticeViews;
        this.accountNo           = accountNo;
    }
}
