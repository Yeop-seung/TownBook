package com.ssafy.townbook.model.entity;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
public class BookLog {
    
    @Id
    @Column(name = "book_log_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookLogNo;
    
    @Column(name = "book_log_state")
    @ColumnDefault("true")
    private Boolean bookLogState;
    
    @Column(name = "book_log_review")
    @Lob
    private String bookLogReview;
    
    @Column(name = "book_log_receiver_no")
    private Long bookLogReceiverNo;
    
//    @NotNull
    @Column(name = "book_log_donate_date_time")
    private LocalDateTime bookLogDonateDateTime;
    
    @Column(name = "book_log_receive_date_time")
    private LocalDateTime bookLogReceiveDateTime;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "`fk-detail_locker-book_log-1`")
    private Locker locker;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "`fk-detail_locker-book_log-2`")
    private DetailLocker detailLocker;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "`fk-account-book_log`")
    private Account account;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "`fk-book-book_log`")
    private Book book;
    
    @OneToMany(mappedBy = "bookLog")
    private List<WishList> wishLists = new ArrayList<>();
    
    public BookLog(Long bookLogNo, Boolean bookLogState, String bookLogReview, Long bookLogReceiverNo,
            LocalDateTime bookLogDonateDateTime, LocalDateTime bookLogReceiveDateTime, Locker locker,
            DetailLocker detailLocker, Account account, Book book, List<WishList> wishLists) {
        this.bookLogNo = bookLogNo;
        this.bookLogState = bookLogState;
        this.bookLogReview = bookLogReview;
        this.bookLogReceiverNo = bookLogReceiverNo;
        this.bookLogDonateDateTime = bookLogDonateDateTime;
        this.bookLogReceiveDateTime = bookLogReceiveDateTime;
        this.locker = locker;
        this.detailLocker = detailLocker;
        this.account = account;
        this.book = book;
        this.wishLists = wishLists;
    }
}
