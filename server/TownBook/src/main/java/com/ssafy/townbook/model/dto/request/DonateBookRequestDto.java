package com.ssafy.townbook.model.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DonateBookRequestDto {
    
    private Long lockerNo;
    private Long detailLockerNo;
    private Long accountNo;
    private String bookIsbn;
    
    public DonateBookRequestDto(Long lockerNo, Long detailLockerNo, Long accountNo, String bookIsbn) {
        this.lockerNo = lockerNo;
        this.detailLockerNo = detailLockerNo;
        this.accountNo = accountNo;
        this.bookIsbn = bookIsbn;
    }
}
