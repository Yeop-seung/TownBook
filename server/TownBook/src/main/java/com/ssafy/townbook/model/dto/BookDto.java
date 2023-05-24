package hide information.townbook.model.dto;

import hide information.townbook.model.entity.Book;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDto {
    
    private String    bookIsbn;
    private String    bookSubject;
    private String    bookTitle;
    private Integer   bookVol;
    private String    bookAuthor;
    private String    bookPublisher;
    private LocalDate bookPublishPredate;
    private String    bookIntroductionURL;
    private String    bookTitleURL;
    private Long bookLogNo;
    
    @Builder
    public BookDto(Book book) {
        this.bookIsbn            = book.getBookIsbn();
        this.bookSubject         = book.getBookSubject();
        this.bookTitle           = book.getBookTitle();
        this.bookVol             = book.getBookVol();
        this.bookAuthor          = book.getBookAuthor();
        this.bookPublisher       = book.getBookPublisher();
        this.bookPublishPredate  = book.getBookPublishPredate();
        this.bookIntroductionURL = book.getBookIntroductionURL();
        this.bookTitleURL        = book.getBookTitleURL();
    }
}
