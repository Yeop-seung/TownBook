package com.ssafy.townbook.model.service;

import com.ssafy.townbook.model.dto.DetailLockerDto;
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
public class DetailLockerServiceImpl implements DetailLockerService {
    
    private DetailLockerRepository detailLockerRepository;
    private LockerRepository lockerRepository;
    
    @Autowired
    public DetailLockerServiceImpl(
            DetailLockerRepository detailLockerRepository,
            LockerRepository lockerRepository) {
        this.detailLockerRepository = detailLockerRepository;
        this.lockerRepository = lockerRepository;
    }
    
    @Override
    public List<DetailLockerDto> findAll() {
        List<DetailLocker> findDetailLockers = detailLockerRepository.findAll();
        return findDetailLockers.stream()
                .map(DetailLockerDto::new)
                .collect(Collectors.toList());
    }
    
    @Override
    public DetailLockerDto findDetailLockerByDetailLockerNo(Long detailLockerNo) {
        DetailLocker findDetailLocker = detailLockerRepository.findDetailLockerByDetailLockerNo(detailLockerNo).get();
        Locker findLocker = findDetailLocker.getLocker();
        return new DetailLockerDto(findDetailLocker, findLocker);
    }
    
    @Override
    public DetailLockerDto addDetailLocker(Long lockerNo) {
        Locker locker = lockerRepository.findLockerByLockerNo(lockerNo);
        DetailLocker detailLocker = new DetailLocker();
        detailLocker.setLocker(locker);
        detailLockerRepository.save(detailLocker);
        return new DetailLockerDto(detailLocker, locker);
    }
}
