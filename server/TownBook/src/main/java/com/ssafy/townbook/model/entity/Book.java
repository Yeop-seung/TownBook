package com.ssafy.townbook.model.entity;

import java.time.LocalDate;
import javax.persistence.*;
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
    @NotNull
    @Column(name = "book_isbn")
    private String bookIsbn;


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


    @Builder
    public Book(String bookIsbn, String bookSubject, String bookTitle, Integer bookVol,
            String bookAuthor, String bookPublisher, LocalDate bookPublishPredate,
            String bookIntroductionURL, String bookTitleURL) {
        this.bookIsbn = bookIsbn;
        this.bookSubject = bookSubject;
        this.bookTitle = bookTitle;
        this.bookVol = bookVol;
        this.bookAuthor = bookAuthor;
        this.bookPublisher = bookPublisher;
        this.bookPublishPredate = bookPublishPredate;
        this.bookIntroductionURL = bookIntroductionURL;
        this.bookTitleURL = bookTitleURL;
    }
}
