package com.ssafy.townbook.model.dto;

import com.ssafy.townbook.model.entity.DetailLocker;
import com.ssafy.townbook.model.entity.Locker;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DetailLockerDto {
    
    private Long detailLockerNo;
    private Locker locker;
    
    public DetailLockerDto(DetailLocker detailLocker, Locker locker) {
        this.detailLockerNo = detailLocker.getDetailLockerNo();
        this.locker = locker;
    }
}
