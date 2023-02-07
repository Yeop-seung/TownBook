package com.ssafy.townbook.model.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReceiveBookRequestDto {
    
    private Long detailLockerNo;
    private Long accountNo;
    
    @Builder
    public ReceiveBookRequestDto(Long detailLockerNo, Long accountNo) {
        this.detailLockerNo = detailLockerNo;
        this.accountNo = accountNo;
    }
}
