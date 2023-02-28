package com.ssafy.townbook.model.dto.response;

import com.ssafy.townbook.model.entity.BookLog;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindBookByLockerResponseDto {
    
    private String    bookIsbn;
    private String    bookSubject;
    private String    bookTitle;
    private Integer   bookVol;
    private String    bookAuthor;
    private String    bookPublisher;
    private LocalDate bookPublisherPredate;
    private String    bookIntroductionURL;
    private String    bookTitleURL;
    private Long      bookLogNo;
    
    @Builder
    public FindBookByLockerResponseDto(BookLog bookLog) {
        
        this.bookIsbn             = bookLog.getBookIsbn();
        this.bookSubject          = bookLog.getBook().getBookSubject();
        this.bookTitle            = bookLog.getBook().getBookTitle();
        this.bookVol              = bookLog.getBook().getBookVol();
        this.bookAuthor           = bookLog.getBook().getBookAuthor();
        this.bookPublisher        = bookLog.getBook().getBookPublisher();
        this.bookPublisherPredate = bookLog.getBook().getBookPublishPredate();
        this.bookIntroductionURL  = bookLog.getBook().getBookIntroductionURL();
        this.bookTitleURL         = bookLog.getBook().getBookTitleURL();
        this.bookLogNo            = bookLog.getBookLogNo();
    }
}
