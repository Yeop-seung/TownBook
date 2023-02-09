package com.ssafy.townbook.model.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DonateBookRequestDto {
    
    private Long   lockerNo;
    private Long   detailLockerNo;
    private Long   accountNo;
    private String bookIsbn;
    
    @Builder
    public DonateBookRequestDto(Long lockerNo, Long detailLockerNo, Long accountNo, String bookIsbn) {
        this.lockerNo       = lockerNo;
        this.detailLockerNo = detailLockerNo;
        this.accountNo      = accountNo;
        this.bookIsbn       = bookIsbn;
    }
}
