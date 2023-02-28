package com.ssafy.townbook.controller;

import com.ssafy.townbook.model.dto.request.CreateLockerRequestDto;
import com.ssafy.townbook.model.dto.request.FindNearLockerRequestDto;
import com.ssafy.townbook.model.dto.response.FindListResponseDto;
import com.ssafy.townbook.model.dto.response.FindOneResponseDto;
import com.ssafy.townbook.model.dto.response.SaveOneResponseDto;
import com.ssafy.townbook.model.service.LockerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/locker")
@RequiredArgsConstructor
public class LockerController {
    
    private LockerService lockerService;
    
    @Autowired
    public LockerController(LockerService lockerService) {
        this.lockerService = lockerService;
    }
    
    /**
     * 전체 보관함 및 보관함에 할당된 세부 보관함 조회
     *
     * @return List<LockerDto>
     */
    @GetMapping("")
    public ResponseEntity<FindListResponseDto> findAllLockers() {
        return new ResponseEntity<FindListResponseDto>(lockerService.findAllLockers(), HttpStatus.OK);
    }
    
    /**
     * 단일 보관함 및 보관함에 할당된 세부 보관함 조회
     *
     * @param lockerNo
     * @return LockerDto
     */
    @GetMapping("/{lockerNo}")
    public ResponseEntity<FindOneResponseDto> findLockerByLockerNo(@PathVariable Long lockerNo) {
        return new ResponseEntity<FindOneResponseDto>(lockerService.findLockerByLockerNo(lockerNo), HttpStatus.OK);
    }
    
    /**
     * 보관함 및 세부 보관함을 생성한다.
     *
     * @param createLockerRequestDto
     * @return
     */
    @PostMapping("/add")
    public ResponseEntity<SaveOneResponseDto> addLocker(@RequestBody CreateLockerRequestDto createLockerRequestDto) {
        String  lockerRegion      = createLockerRequestDto.getLockerRegion();
        Integer detailLockerCount = createLockerRequestDto.getDetailLockerCount();
        Double  lockerLatitude    = createLockerRequestDto.getLockerLatitude();
        Double  lockerLongitude   = createLockerRequestDto.getLockerLongitude();
        return new ResponseEntity<SaveOneResponseDto>(
                lockerService.addLocker(lockerRegion, detailLockerCount, lockerLatitude, lockerLongitude),
                HttpStatus.OK);
    }
    
    /**
     * 이용자의 접속 위치에서 가까운 순서로 보관함을 조회한다.
     *
     * @param findNearLockerRequestDto
     * @return List<LockerDto>
     */
    @PostMapping("/findNearLocker")
    public ResponseEntity<FindListResponseDto> findNearLocker(
            @RequestBody FindNearLockerRequestDto findNearLockerRequestDto) {
        Double userLatitude  = findNearLockerRequestDto.getUserLatitude();
        Double userLongitude = findNearLockerRequestDto.getUserLongitude();
        return new ResponseEntity<>(lockerService.findNearLocker(userLatitude, userLongitude), HttpStatus.OK);
    }
}
