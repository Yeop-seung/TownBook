package com.ssafy.townbook.model.entity;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
    @Column(name = "book_no")
    private Long bookNo;


    @Column(name = "book_state")
    @ColumnDefault("true")
    private Boolean bookState;

    @Column(name = "book_review")
    @Lob
    private String bookReview;

    @Column(name = "book_receiver_id")
    private String bookReceiverId;

    @NotNull
    @Column(name = "book_donate_date")
    private LocalDateTime donateDate;

    @Column(name = "book_receive_date")
    private LocalDate bookReceiveDate;


    @OneToOne
    @JoinColumn(name = "`fk-detail_locker-book-1`")
    private Locker locker;

    @OneToOne
    @JoinColumn(name = "`fk-detail_locker-book-2`")
    private DetailLocker detailLocker;

    @OneToOne
    @JoinColumn(name = "`fk-account-book`")
    private Account account;

    @OneToMany(mappedBy = "bookLog")
    private List<WishList> wishLists = new ArrayList<>();

    @Builder
    public BookLog(Long bookNo, Boolean bookState, String bookReview, String bookReceiverId,
            LocalDateTime donateDate, LocalDate bookReceiveDate, Locker locker,
            DetailLocker detailLocker, Account account) {
        this.bookNo = bookNo;
        this.bookState = bookState;
        this.bookReview = bookReview;
        this.bookReceiverId = bookReceiverId;
        this.donateDate = donateDate;
        this.bookReceiveDate = bookReceiveDate;
        this.locker = locker;
        this.detailLocker = detailLocker;
        this.account = account;
    }
}
