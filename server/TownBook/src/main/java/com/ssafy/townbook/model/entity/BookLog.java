package com.ssafy.townbook.model.entity;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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
public class BookLog {
    
    @Id
    @Column(name = "book_log_no")
    private Long bookLogNo;
    
    
    @Column(name = "book_log_state")
    @ColumnDefault("true")
    private Boolean bookLogState;
    
    @Column(name = "book_log_review")
    @Lob
    private String bookLogReview;
    
    @Column(name = "book_log_receiver_no")
    private Long bookLogReceiverNo;
    
    @NotNull
    @Column(name = "book_log_donate_date")
    private LocalDateTime bookLogDonateDate;
    
    @Column(name = "book_log_receive_date")
    private LocalDate bookLogReceiveDate;
    
    
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

    public BookLog(Long bookLogNo, Boolean bookLogState, String bookLogReview, Long bookReceiverNo, LocalDateTime bookLogDonateDate, LocalDate bookLogReceiveDate, Locker locker, DetailLocker detailLocker, Account account, Book book, List<WishList> wishLists) {
        this.bookLogNo = bookLogNo;
        this.bookLogState = bookLogState;
        this.bookLogReview = bookLogReview;
        this.bookLogReceiverNo = bookLogReceiverNo;
        this.bookLogDonateDate = bookLogDonateDate;
        this.bookLogReceiveDate = bookLogReceiveDate;
        this.locker = locker;
        this.detailLocker = detailLocker;
        this.account = account;
        this.book = book;
        this.wishLists = wishLists;
    }
}
