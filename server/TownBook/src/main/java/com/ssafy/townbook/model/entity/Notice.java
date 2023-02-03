package com.ssafy.townbook.model.entity;

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
    
    @NotNull
    @Column(name = "notice_write_time")
    private LocalDateTime noticeWriteDateTime;
    
    @Column(name = "notice_state")
    @ColumnDefault("true")
    private Boolean noticeState;
    
    @Column(name = "notice_views")
    @ColumnDefault("0")
    private Integer noticeViews;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "`fk-account-notice`")
    private Account account;
    
    @Builder
    public Notice(Long noticeNo, String noticeTitle, String noticeContent,
            LocalDateTime noticeWriteDateTime, Boolean noticeState, Integer noticeViews,
            Integer noticeCategory,
            Account account) {
        this.noticeNo = noticeNo;
        this.noticeTitle = noticeTitle;
        this.noticeContent = noticeContent;
        this.noticeWriteDateTime = noticeWriteDateTime;
        this.noticeCategory = noticeCategory;
        this.noticeState = noticeState;
        this.noticeViews = noticeViews;
        this.account = account;
    }
}
