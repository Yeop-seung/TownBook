package com.ssafy.townbook.model.dto;

import com.ssafy.townbook.model.entity.BookLog;
import com.ssafy.townbook.model.entity.WishList;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookLogDto {
    
    private Long           bookLogNo;
    private Boolean        bookLogState;
    private String         bookLogReview;
    private Long           bookLogReceiverNo;
    private LocalDateTime  bookLogDonateDateTime;
    private LocalDateTime  bookLogReceiveDateTime;
    private List<WishList> wishLists = new ArrayList<>();
    private Long           accountNo;
    private String         bookIsbn;
    private Long           lockerNo;
    private Long           detailLockerNo;
    
    @Builder
    public BookLogDto(BookLog bookLog) {
        this.bookLogNo              = bookLog.getBookLogNo();
        this.bookLogState           = bookLog.getBookLogState();
        this.bookLogReview          = bookLog.getBookLogReview();
        this.bookLogReceiverNo      = bookLog.getBookLogReceiverNo();
        this.bookLogDonateDateTime  = bookLog.getBookLogDonateDateTime();
        this.bookLogReceiveDateTime = bookLog.getBookLogReceiveDateTime();
        this.wishLists              = bookLog.getWishLists();
        this.accountNo              = bookLog.getAccountNo();
        this.bookIsbn               = bookLog.getBookIsbn();
    }
}