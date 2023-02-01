package com.ssafy.townbook.model.dto;

import com.ssafy.townbook.model.entity.DetailLocker;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DetailLockerDto {
    
    private Long detailLockerNo;
    private Boolean detailLockerIsEmpty;
    
    public DetailLockerDto(DetailLocker detailLocker) {
        
        this.detailLockerNo = detailLocker.getDetailLockerNo();
        this.detailLockerIsEmpty = detailLocker.getDetailLockerIsEmpty();
    }
}
