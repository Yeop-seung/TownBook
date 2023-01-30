package com.ssafy.townbook.model.service;

import com.ssafy.townbook.model.dto.LockerDto;
import com.ssafy.townbook.model.entity.Locker;
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
    
    @Autowired
    public LockerServiceImpl(LockerRepository lockerRepository) {
        this.lockerRepository = lockerRepository;
    }
    
    @Override
    public List<LockerDto> findAll() {
        List<Locker> findLockers = lockerRepository.findAll();
        return findLockers.stream()
                .map(LockerDto::new)
                .collect(Collectors.toList());
    }
    
    @Override
    public LockerDto findLockerByLockerNo(Long lockerNo) {
        Locker findLocker = lockerRepository.findLockerByLockerNo(lockerNo).get();
        return new LockerDto(findLocker);
    }
    
    @Override
    @Transactional
    public LockerDto addLocker(String lockerRegion) {
        Locker locker = new Locker();
        locker.setLockerRegion(lockerRegion);
        lockerRepository.save(locker);
        return new LockerDto(locker);
    }
}
