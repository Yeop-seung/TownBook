package com.ssafy.townbook.model.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReceiveBookRequestDto {
    
    private Long detailLockerNo;
    private Long accountNo;
    
    public ReceiveBookRequestDto(Long detailLockerNo, Long accountNo) {
        this.detailLockerNo = detailLockerNo;
        this.accountNo = accountNo;
    }
}
