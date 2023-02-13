package com.ssafy.townbook.model.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindBookInLibraryRequestDto {
    
    private String bookIsbn;
    
    @Builder
    public FindBookInLibraryRequestDto(String bookIsbn) {
        this.bookIsbn = bookIsbn;
    }
}
