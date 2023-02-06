package com.ssafy.townbook.model.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchTitleRequestDto {
    
    private String bookTitle;
    private Double lockerLatitude;
    private Double lockerLongitude;
    
    @Builder
    public SearchTitleRequestDto(String bookTitle, String lockerLatitude, String lockerLongitude) {
        this.bookTitle = bookTitle;
        this.lockerLatitude = Double.parseDouble(lockerLatitude);
        this.lockerLongitude = Double.parseDouble(lockerLongitude);
    }
}
