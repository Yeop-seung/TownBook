package com.ssafy.townbook.model.service;

import com.ssafy.townbook.model.dto.LockerDto;
import java.util.List;

public interface LockerService {
    
    List<LockerDto> findAll();
    
    LockerDto findLockerByLockerNo(Long lockerNo);
    
    LockerDto addLocker(String lockerRegion);
}
