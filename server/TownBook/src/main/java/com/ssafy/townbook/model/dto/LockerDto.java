package com.ssafy.townbook.model.dto;

import com.ssafy.townbook.model.entity.DetailLocker;
import com.ssafy.townbook.model.entity.Locker;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
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
    private List<DetailLockerDto> detailLocker = new ArrayList<>();
    private int lockerStorage;
    
    public LockerDto(Locker locker) {
        this.lockerNo = locker.getLockerNo();
        this.lockerBookCnt = locker.getLockerBookCnt();
        this.lockerRegion = locker.getLockerRegion();
        this.lockerLatitude = locker.getLockerLatitude();
        this.lockerLongitude = locker.getLockerLongitude();
        
        // 연관 관계의 무한 참조를 방지하기 위해 DetailLockerDto 사용
        List<DetailLocker> findDetailLockers = locker.getDetailLocker();
        this.detailLocker = findDetailLockers.stream()
                .map(DetailLockerDto::new)
                .collect(Collectors.toList());
        
        // 보관함 저장 공간
        this.lockerStorage = 0;
        for (int i = 0; i < findDetailLockers.size(); i++) {
            if (findDetailLockers.get(i).getDetailLockerIsEmpty()) {
                this.lockerStorage++;
            }
        }
    }
}
