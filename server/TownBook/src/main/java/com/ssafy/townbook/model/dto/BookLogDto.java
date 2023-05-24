package hide information.townbook.model.dto;

import hide information.townbook.model.entity.BookLog;
import hide information.townbook.model.entity.WishList;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookLogDto {
    
    private Long          bookLogNo;
    private Boolean       bookLogState;
    private String        bookLogReview;
    private Long          bookLogReceiverNo;
    private LocalDateTime bookLogDonateDateTime;
    private LocalDateTime bookLogReceiveDateTime;
    private List<Long>    wishListNo = new ArrayList<>();
    private Long          accountNo;
    private String        bookIsbn;
    private Long          lockerNo;
    private Long          detailLockerNo;
    
    @Builder
    public BookLogDto(BookLog bookLog) {
        this.bookLogNo              = bookLog.getBookLogNo();
        this.bookLogState           = bookLog.getBookLogState();
        this.bookLogReview          = bookLog.getBookLogReview();
        this.bookLogReceiverNo      = bookLog.getBookLogReceiverNo();
        this.bookLogDonateDateTime  = bookLog.getBookLogDonateDateTime();
        this.bookLogReceiveDateTime = bookLog.getBookLogReceiveDateTime();
        for (WishList wishList : bookLog.getWishLists()) {
            this.wishListNo.add(wishList.getWishListNo());
        }
        this.accountNo      = bookLog.getAccountNo();
        this.bookIsbn       = bookLog.getBookIsbn();
        this.lockerNo       = bookLog.getLockerNo();
        this.detailLockerNo = bookLog.getDetailLockerNo();
    }
}