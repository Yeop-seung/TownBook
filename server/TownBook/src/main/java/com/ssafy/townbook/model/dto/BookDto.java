package com.ssafy.townbook.model.dto;

import com.ssafy.townbook.model.entity.Book;
import java.time.LocalDate;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookDto {
    
    private String bookIsbn;
    private String bookSubject;
    private String bookTitle;
    private Integer bookVol;
    private String bookAuthor;
    private String bookPublisher;
    private LocalDate bookPublishPredate;
    private String bookIntroductionURL;
    private String bookTitleURL;
    
    public BookDto(Book book) {
        this.bookIsbn = book.getBookIsbn();
        this.bookSubject = book.getBookSubject();
        this.bookTitle = book.getBookTitle();
        this.bookVol = book.getBookVol();
        this.bookAuthor = book.getBookAuthor();
        this.bookPublisher = book.getBookPublisher();
        this.bookPublishPredate = book.getBookPublishPredate();
        this.bookIntroductionURL = book.getBookIntroductionURL();
        this.bookTitleURL = book.getBookTitleURL();
    }
}
