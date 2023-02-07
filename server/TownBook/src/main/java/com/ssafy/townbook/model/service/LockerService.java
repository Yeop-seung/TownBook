package com.ssafy.townbook.model.service;

import com.ssafy.townbook.model.dto.LockerDto;
import java.util.List;

public interface LockerService {
    
    /**
     * 전체 보관함 및 보관함에 할당된 세부 보관함 조회
     *
     * @return List<LockerDto>
     */
    List<LockerDto> findAll();
    
    /**
     * 단일 보관함 및 보관함에 할당된 세부 보관함 조회
     *
     * @param lockerNo
     * @return LockerDto
     */
    LockerDto findLockerByLockerNo(Long lockerNo);
    
    /**
     * 보관함 및 세부 보관함을 생성한다.
     *
     * @param lockerRegion
     * @param detailLockerCount
     * @return Boolean
     */
    Boolean addLocker(String lockerRegion, int detailLockerCount, Double lockerLatitude, Double lockerLongitude);
}
