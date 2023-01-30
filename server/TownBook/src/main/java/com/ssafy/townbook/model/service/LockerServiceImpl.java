package com.ssafy.townbook.model.service;

import com.ssafy.townbook.model.dto.LockerDto;
import com.ssafy.townbook.model.entity.DetailLocker;
import com.ssafy.townbook.model.entity.Locker;
import com.ssafy.townbook.model.repository.DetailLockerRepository;
import com.ssafy.townbook.model.repository.LockerRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LockerServiceImpl implements LockerService {
    
    private LockerRepository lockerRepository;
    private DetailLockerRepository detailLockerRepository;
    
    @Autowired
    public LockerServiceImpl(LockerRepository lockerRepository, DetailLockerRepository detailLockerRepository) {
        this.lockerRepository = lockerRepository;
        this.detailLockerRepository = detailLockerRepository;
    }
    
    /**
     * 전체 보관함 및 보관함에 할당된 세부 보관함 조회
     * 보관함 DTO로 변환하여 반환
     *
     * @return List
     */
    @Override
    public List<LockerDto> findAll() {
        List<Locker> findLockers = lockerRepository.findAll();
        return findLockers.stream()
                .map(LockerDto::new)
                .collect(Collectors.toList());
    }
    
    /**
     * 단일 보관함 및 보관함에 할당된 세부 보관함 조회
     * 보관함 DTO로 변환하여 반환
     *
     * @param lockerNo
     * @return LockerDto
     */
    @Override
    public LockerDto findLockerByLockerNo(Long lockerNo) {
        Locker findLocker = lockerRepository.findLockerByLockerNo(lockerNo);
        return new LockerDto(findLocker);
    }
    
    /**
     * 보관함 및 세부 보관함을 생성한다.
     * 보관함 이름, 세부 보관함 개수 PathVariable
     *
     * @param lockerRegion
     * @param detailLockerCount
     * @return LockerDto
     */
    @Override
    @Transactional
    public LockerDto addLocker(String lockerRegion, int detailLockerCount) {
        Locker locker = new Locker();
        locker.setLockerRegion(lockerRegion);
        lockerRepository.save(locker);
        while (detailLockerCount-- > 0) {
            DetailLocker detailLocker = new DetailLocker();
            locker.addDetailLocker(detailLocker);
            detailLockerRepository.save(detailLocker);
        }
        return new LockerDto(locker);
    }
}

