package hide information.townbook.model.entity;


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
    
    @NotNull
    @Column(name = "book_log_donate_date_time")
    private LocalDateTime bookLogDonateDateTime;
    
    @Column(name = "book_log_receive_date_time")
    private LocalDateTime bookLogReceiveDateTime;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "`fk-locker-book_log`", insertable = false, updatable = false)
    private Locker locker;
    
    @Column(name = "`fk-locker-book_log`")
    private Long lockerNo;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "`fk-detail_locker-book_log`", insertable = false, updatable = false)
    private DetailLocker detailLocker;
    
    @Column(name = "`fk-detail_locker-book_log`")
    private Long detailLockerNo;
    
    @OneToMany(mappedBy = "bookLog")
    private List<WishList> wishLists = new ArrayList<>();
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "`fk-account-book_log`", insertable = false, updatable = false)
    private Account account;
    
    @Column(name = "`fk-account-book_log`")
    private Long accountNo;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "`fk-book-book_log`", insertable = false, updatable = false)
    private Book book;
    
    @Column(name = "`fk-book-book_log`")
    private String bookIsbn;
    
    @Builder
    public BookLog(Long lockerNo, Long detailLockerNo, Long accountNo, String bookIsbn) {
        this.lockerNo              = lockerNo;
        this.detailLockerNo        = detailLockerNo;
        this.accountNo             = accountNo;
        this.bookIsbn              = bookIsbn;
        this.bookLogDonateDateTime = LocalDateTime.now();
    }
    
    @Builder
    public BookLog(Long bookLogNo, Boolean bookLogState, String bookLogReview, Long bookLogReceiverNo,
            LocalDateTime bookLogDonateDateTime, LocalDateTime bookLogReceiveDateTime, Locker locker, Long lockerNo,
            DetailLocker detailLocker, Long detailLockerNo, List<WishList> wishLists, Long accountNo, String bookIsbn) {
        this.bookLogNo              = bookLogNo;
        this.bookLogState           = bookLogState;
        this.bookLogReview          = bookLogReview;
        this.bookLogReceiverNo      = bookLogReceiverNo;
        this.bookLogDonateDateTime  = bookLogDonateDateTime;
        this.bookLogReceiveDateTime = bookLogReceiveDateTime;
        this.locker                 = locker;
        this.lockerNo               = lockerNo;
        this.detailLocker           = detailLocker;
        this.detailLockerNo         = detailLockerNo;
        this.wishLists              = wishLists;
        this.accountNo              = accountNo;
        this.bookIsbn               = bookIsbn;
    }
}
