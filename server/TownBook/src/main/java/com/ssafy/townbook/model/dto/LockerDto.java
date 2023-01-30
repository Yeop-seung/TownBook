package com.ssafy.townbook.model.dto;

import com.ssafy.townbook.model.entity.DetailLocker;
import com.ssafy.townbook.model.entity.Locker;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LockerDto {
    
    private Long lockerNo;
    private Integer lockerBookCnt;
    private String lockerRegion;
    private String lockerLatitude;
    private String lockerLongitude;
    private List<DetailLocker> detailLocker = new ArrayList<>();
    
    public LockerDto(Locker locker) {
        this.lockerNo = locker.getLockerNo();
        this.lockerBookCnt = locker.getLockerBookCnt();
        this.lockerRegion = locker.getLockerRegion();
        this.lockerLatitude = locker.getLockerLatitude();
        this.lockerLongitude = locker.getLockerLongitude();
        this.detailLocker =locker.getDetailLocker();
    }
}
