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
    private Long bookNo;
    
    
    @Column(name = "book_log_state")
    @ColumnDefault("true")
    private Boolean bookState;
    
    @Column(name = "book_log_review")
    @Lob
    private String bookReview;
    
    @Column(name = "book_log_receiver_id")
    private String bookReceiverId;
    
    @NotNull
    @Column(name = "book_log_donate_date")
    private LocalDateTime bookDonateDate;
    
    @Column(name = "book_log_receive_date")
    private LocalDate bookReceiveDate;
    
    
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

    public BookLog(Long bookNo, Boolean bookState, String bookReview, String bookReceiverId, LocalDateTime bookDonateDate, LocalDate bookReceiveDate, Locker locker, DetailLocker detailLocker, Account account, Book book, List<WishList> wishLists) {
        this.bookNo = bookNo;
        this.bookState = bookState;
        this.bookReview = bookReview;
        this.bookReceiverId = bookReceiverId;
        this.bookDonateDate = bookDonateDate;
        this.bookReceiveDate = bookReceiveDate;
        this.locker = locker;
        this.detailLocker = detailLocker;
        this.account = account;
        this.book = book;
        this.wishLists = wishLists;
    }
}
