package com.ssafy.townbook.model.service;

import com.ssafy.townbook.model.dto.DetailLockerDto;
import java.util.List;

public interface DetailLockerService {
    
    List<DetailLockerDto> findAll();
    
    DetailLockerDto findDetailLockerByDetailLockerNo(Long detailLockerNo);
    
    DetailLockerDto addDetailLocker(Long lockerNo);
}
