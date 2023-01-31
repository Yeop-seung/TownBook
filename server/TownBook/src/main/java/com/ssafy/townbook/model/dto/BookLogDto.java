package com.ssafy.townbook.model.dto;

import com.ssafy.townbook.model.entity.BookLog;
import com.ssafy.townbook.model.entity.WishList;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookLogDto {
    
    private Long bookLogNo;
    private Boolean bookLogState;
    private String bookLogReview;
    private Long bookLogReceiverNo;
    private LocalDateTime bookLogDonateDateTime;
    private LocalDateTime bookLogReceiveDateTime;
    private LockerDto lockerDto;
    private DetailLockerDto detailLockerDto;
    private AdminDto adminDto;
    private BookDto bookDto;
    private List<WishList> wishLists = new ArrayList<>();
    
    public BookLogDto(BookLog bookLog) {
        this.bookLogNo = bookLog.getBookLogNo();
        this.bookLogState = bookLog.getBookLogState();
        this.bookLogReview = bookLog.getBookLogReview();
        this.bookLogReceiverNo = bookLog.getBookLogReceiverNo();
        this.bookLogDonateDateTime = bookLog.getBookLogDonateDateTime();
        this.bookLogReceiveDateTime = bookLog.getBookLogReceiveDateTime();
        this.wishLists = bookLog.getWishLists();

        // 연관 관계의 무한 참조를 방지하기 위해 DTO 사용
        this.adminDto = new AdminDto(bookLog.getAccount());
        this.bookDto = new BookDto(bookLog.getBook());
        this.detailLockerDto = new DetailLockerDto(bookLog.getDetailLocker());
        this.lockerDto = new LockerDto(bookLog.getLocker());
    }
}