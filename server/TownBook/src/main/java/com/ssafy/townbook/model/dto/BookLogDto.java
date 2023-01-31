package com.ssafy.townbook.model.dto;

import com.ssafy.townbook.model.entity.Account;
import com.ssafy.townbook.model.entity.Book;
import com.ssafy.townbook.model.entity.BookLog;
import com.ssafy.townbook.model.entity.DetailLocker;
import com.ssafy.townbook.model.entity.Locker;
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
    private Locker locker;
    private DetailLocker detailLocker;
    private Account account;
    private Book book;
    private List<WishList> wishLists = new ArrayList<>();
    
    public BookLogDto(BookLog bookLog) {
        this.bookLogNo = bookLog.getBookLogNo();
        this.bookLogState = bookLog.getBookLogState();
        this.bookLogReview = bookLog.getBookLogReview();
        this.bookLogReceiverNo = bookLog.getBookLogReceiverNo();
        this.bookLogDonateDateTime = bookLog.getBookLogDonateDateTime();
        this.bookLogReceiveDateTime = bookLog.getBookLogReceiveDateTime();
        this.locker = bookLog.getLocker();
        this.detailLocker = bookLog.getDetailLocker();
        this.account = bookLog.getAccount();
        this.book = bookLog.getBook();
        this.wishLists = bookLog.getWishLists();
    }
}