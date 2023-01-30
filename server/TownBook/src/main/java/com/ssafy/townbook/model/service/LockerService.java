package com.ssafy.townbook.model.service;

import com.ssafy.townbook.model.dto.LockerDto;
import java.util.List;

public interface LockerService {
    
    /**
     * 전체 보관함 및 보관함에 할당된 세부 보관함 조회
     * 보관함 DTO로 변환하여 반환
     *
     * @return List
     */
    List<LockerDto> findAll();
    
    /**
     * 단일 보관함 및 보관함에 할당된 세부 보관함 조회
     * 보관함 DTO로 변환하여 반환
     *
     * @param lockerNo
     * @return LockerDto
     */
    LockerDto findLockerByLockerNo(Long lockerNo);
    
    /**
     * 보관함 및 세부 보관함을 생성한다.
     * 보관함 이름, 세부 보관함 개수 PathVariable
     *
     * @param lockerRegion
     * @param detailLockerCount
     * @return LockerDto
     */
    LockerDto addLocker(String lockerRegion, int detailLockerCount, String lockerLatitude, String lockerLongitude);
}
