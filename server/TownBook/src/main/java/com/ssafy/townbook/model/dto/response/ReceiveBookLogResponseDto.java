package com.ssafy.townbook.model.dto.response;

import com.ssafy.townbook.model.entity.Book;
import com.ssafy.townbook.model.entity.DetailLocker;
import com.ssafy.townbook.model.entity.Locker;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReceiveBookLogResponseDto {
    
    private String bookTitle;
    private Long   detailLockerNo;
    private Long   detailLockerNoInLocker;
    private String bookIntroductionURL;
    private String bookTitleURL;
    
    @Builder
    public ReceiveBookLogResponseDto(Book book, Locker locker, DetailLocker detailLocker) {
        this.bookTitle      = book.getBookTitle();
        this.detailLockerNo = detailLocker.getDetailLockerNo();
        
        Long idx = locker.getDetailLocker().get(0).getDetailLockerNo();
        this.detailLockerNoInLocker = this.detailLockerNo - idx + 1;
        this.bookIntroductionURL    = book.getBookIntroductionURL();
        this.bookTitleURL           = book.getBookTitleURL();
    }
}
