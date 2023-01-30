package com.ssafy.townbook.model.dto;

import com.ssafy.townbook.model.entity.Locker;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LockerDto {
    
    private Long lockerNo;
    private Integer lockerBookCnt;
    private String lockerRegion;
    
    public LockerDto(Locker locker) {
        this.lockerNo = locker.getLockerNo();
        this.lockerBookCnt = locker.getLockerBookCnt();
        this.lockerRegion = locker.getLockerRegion();
    }
}
