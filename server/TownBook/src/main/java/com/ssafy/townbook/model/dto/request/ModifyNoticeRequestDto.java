package hide information.townbook.model.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModifyNoticeRequestDto {
    
    private Long    noticeNo;
    private String  noticeTitle;
    private String  noticeContent;
    private Integer noticeCategory;
    
    @Builder
    public ModifyNoticeRequestDto(Long noticeNo, String noticeTitle, String noticeContent, Integer noticeCategory) {
        this.noticeNo       = noticeNo;
        this.noticeTitle    = noticeTitle;
        this.noticeContent  = noticeContent;
        this.noticeCategory = noticeCategory;
    }
}