package hide information.townbook.model.service;

import hide information.townbook.model.dto.response.FindListResponseDto;
import hide information.townbook.model.dto.response.FindOneResponseDto;
import hide information.townbook.model.dto.response.SaveOneResponseDto;

public interface LockerService {
    
    /**
     * 전체 보관함 및 보관함에 할당된 세부 보관함 조회
     *
     * @return List<LockerDto>
     */
    FindListResponseDto findAllLockers();
    
    /**
     * 단일 보관함 및 보관함에 할당된 세부 보관함 조회
     *
     * @param lockerNo
     * @return LockerDto
     */
    FindOneResponseDto findLockerByLockerNo(Long lockerNo);
    
    /**
     * 보관함 및 세부 보관함을 생성한다.
     *
     * @param lockerRegion
     * @param detailLockerCount
     * @return Boolean
     */
    SaveOneResponseDto addLocker(String lockerRegion, int detailLockerCount, Double lockerLatitude,
            Double lockerLongitude);
    
    /**
     * 이용자의 접속 위치에서 가까운 순서로 보관함을 조회한다.
     *
     * @param userLatitude, userLongitude
     * @return List<LockerDto>
     */
    FindListResponseDto findNearLocker(Double userLatitude, Double userLongitude);
}
