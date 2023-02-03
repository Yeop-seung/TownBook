package com.ssafy.townbook.model.dto.response;

import com.ssafy.townbook.model.dto.BookLogDto;
import com.ssafy.townbook.model.dto.DetailLockerDto;
import com.ssafy.townbook.model.entity.BookLog;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReceiveBookLogDto {
    
    private String bookTitle;
    private DetailLockerDto detailLockerDto;
    
    @Builder
    public ReceiveBookLogDto(BookLog bookLog) {
        BookLogDto bookLogDto = new BookLogDto(bookLog);
        this.bookTitle = bookLogDto.getBookDto().getBookTitle();
        this.detailLockerDto = bookLogDto.getDetailLockerDto();
    }
}
