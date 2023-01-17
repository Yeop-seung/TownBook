package com.ssafy.TownBook.model.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class Book {

    @Id
    @Column(name = "book_no")
    private Long bookNo;

    @NotNull
    @Column(name = "book_isbn")
    private String bookIsbn;

    @NotNull
    @Column(name = "book_cnt", columnDefinition = "Integer default 1")
    private Integer bookCnt;

    @NotNull
    @Column(name = "book_subject")
    private String bookSubject;

    @NotNull
    @Column(name = "book_title")
    private String bookTitle;

    @Column(name = "book_vol")
    private Integer bookVol;

    @NotNull
    @Column(name = "book_author")
    private String bookAuthor;

    @NotNull
    @Column(name = "book_publisher")
    private String bookPublisher;

    @Column(name = "book_publish_predate")
    private LocalDate bookPublishPredate;

    @Column(name = "book_introduction_url")
    private String bookIntroductionURL;

    @Column(name = "book_title_url")
    private String bookTitleURL;

    @Column(name = "book_photo", columnDefinition = "String default null.png")
    private String bookPhoto;

    @Column(name = "book_review")
    private String bookReview;

    @Column(name = "book_receiver_id")
    private String bookReceiverId;

    @NotNull
    @Column(name = "book_donate_date")
    private LocalDateTime donateDate;

    @Column(name = "book_receive_date")
    private LocalDate bookReceiveDate;

    @Column(name = "fk-detail_locker-book-1")
    private List<Locker> locker = new ArrayList<>();

    @Column(name = "fk-detail_locker-book-2")
    private List<DetailLocker> detailLocker = new ArrayList<>();

    @Column(name = "fk-account-book")
    private List<Account> account = new ArrayList<>();

    @Builder
    public Book(Long bookNo, String bookIsbn, Integer bookCnt, String bookSubject, String bookTitle,
            Integer bookVol, String bookAuthor, String bookPublisher, LocalDate bookPublishPredate,
            String bookIntroductionURL, String bookTitleURL, String bookPhoto, String bookReview,
            String bookReceiverId, LocalDateTime donateDate, LocalDate bookReceiveDate,
            List<Locker> locker, List<DetailLocker> detailLocker, List<Account> account) {
        this.bookNo = bookNo;
        this.bookIsbn = bookIsbn;
        this.bookCnt = bookCnt;
        this.bookSubject = bookSubject;
        this.bookTitle = bookTitle;
        this.bookVol = bookVol;
        this.bookAuthor = bookAuthor;
        this.bookPublisher = bookPublisher;
        this.bookPublishPredate = bookPublishPredate;
        this.bookIntroductionURL = bookIntroductionURL;
        this.bookTitleURL = bookTitleURL;
        this.bookPhoto = bookPhoto;
        this.bookReview = bookReview;
        this.bookReceiverId = bookReceiverId;
        this.donateDate = donateDate;
        this.bookReceiveDate = bookReceiveDate;
        this.locker = locker;
        this.detailLocker = detailLocker;
        this.account = account;
    }
}
