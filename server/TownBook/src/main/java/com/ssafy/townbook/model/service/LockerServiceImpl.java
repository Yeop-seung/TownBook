package hide information.townbook.model.service;

import hide information.townbook.model.dto.LockerDto;
import hide information.townbook.model.dto.response.FindListResponseDto;
import hide information.townbook.model.dto.response.FindOneResponseDto;
import hide information.townbook.model.dto.response.SaveOneResponseDto;
import hide information.townbook.model.entity.DetailLocker;
import hide information.townbook.model.entity.Locker;
import hide information.townbook.model.repository.DetailLockerRepository;
import hide information.townbook.model.repository.LockerRepository;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LockerServiceImpl implements LockerService {
    
    private LockerRepository       lockerRepository;
    private DetailLockerRepository detailLockerRepository;
    
    @Autowired
    public LockerServiceImpl(LockerRepository lockerRepository, DetailLockerRepository detailLockerRepository) {
        this.lockerRepository       = lockerRepository;
        this.detailLockerRepository = detailLockerRepository;
    }
    
    /**
     * 전체 보관함 및 보관함에 할당된 세부 보관함 조회
     *
     * @return List<LockerDto>
     */
    @Override
    public FindListResponseDto findAllLockers() {
        Optional<List<Locker>> findLockers = Optional.ofNullable(lockerRepository.findAll());
        return new FindListResponseDto(findLockers.get().stream()
                .map(LockerDto::new)
                .collect(Collectors.toList()));
    }
    
    /**
     * 단일 보관함 및 보관함에 할당된 세부 보관함 조회
     *
     * @param lockerNo
     * @return LockerDto
     */
    @Override
    public FindOneResponseDto findLockerByLockerNo(Long lockerNo) {
        Locker findLocker = lockerRepository.findLockerByLockerNo(lockerNo).get();
        return new FindOneResponseDto(new LockerDto(findLocker));
    }
    
    /**
     * 보관함 및 세부 보관함을 생성한다.
     *
     * @param lockerRegion
     * @param detailLockerCount
     * @return Boolean
     */
    @Override
    @Transactional
    public SaveOneResponseDto addLocker(String lockerRegion, int detailLockerCount, Double lockerLatitude,
            Double lockerLongitude) {
        try {
            Locker locker = new Locker();
            locker.setLockerRegion(lockerRegion);
            locker.setLockerLatitude(lockerLatitude);
            locker.setLockerLongitude(lockerLongitude);
            lockerRepository.save(locker);
            
            for (int i = 1; i <= detailLockerCount; i++) {
                DetailLocker detailLocker = new DetailLocker();
                locker.addDetailLocker(detailLocker);
                detailLocker.setDetailLockerNoInLocker((long) i);
                detailLocker.setBookInDetailLocker(null);
                detailLockerRepository.save(detailLocker);
            }
            
            return new SaveOneResponseDto(true);
        } catch (Exception e) {
            return new SaveOneResponseDto(e.getMessage());
        }
    }
    
    /**
     * 이용자의 접속 위치에서 가까운 순서로 보관함을 조회한다.
     *
     * @param userLatitude, userLongitude
     * @return List<LockerDto>
     */
    @Override
    public FindListResponseDto findNearLocker(Double userLatitude, Double userLongitude) {
        List<Locker> findAllLockers = lockerRepository.findAll();
        
        List<LockerDto> findAllLockersDtos = new ArrayList<>();
        for (Locker locker : findAllLockers) {
            LockerDto lockerDto = new LockerDto(locker, userLatitude, userLongitude);
            findAllLockersDtos.add(lockerDto);
        }
        
        Comparator<LockerDto> comparator = Comparator.comparing(LockerDto::getLockerDistance,
                Comparator.naturalOrder());
        
        List<LockerDto> findNearLocker = findAllLockersDtos.stream()
                .sorted(comparator)
                .collect(Collectors.toList());
        
        return new FindListResponseDto(findNearLocker);
    }
}

