package com.ssafy.townbook.controller;

import com.ssafy.townbook.model.dto.LockerDto;
import com.ssafy.townbook.model.service.LockerService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    public ResponseEntity<List<LockerDto>> lockers() {
        return new ResponseEntity<List<LockerDto>>(lockerService.findAll(), HttpStatus.OK);
    }
    
    /**
     * 단일 보관함 및 보관함에 할당된 세부 보관함 조회
     *
     * @param lockerNo
     * @return LockerDto
     */
    @GetMapping("/{lockerNo}")
    public ResponseEntity<LockerDto> findLockerByLockerNo(@PathVariable Long lockerNo) {
        return new ResponseEntity<LockerDto>(lockerService.findLockerByLockerNo(lockerNo), HttpStatus.OK);
    }
    
    /**
     * 보관함 및 세부 보관함을 생성한다.
     *
     * @param lockerRegion
     * @param detailLockerCount
     * @return Boolean
     */
    @PostMapping("/add/{lockerRegion}/{detailLockerCount}/{lockerLatitude}/{lockerLongitude}")
    public ResponseEntity<Boolean> addLocker(
            @PathVariable String lockerRegion, @PathVariable int detailLockerCount,
            @PathVariable Double lockerLatitude, @PathVariable Double lockerLongitude) {
        return new ResponseEntity<Boolean>(
                lockerService.addLocker(lockerRegion, detailLockerCount, lockerLatitude, lockerLongitude),
                HttpStatus.OK);
    }
}
