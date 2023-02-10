package com.ssafy.townbook.model.service;

import com.ssafy.townbook.model.dto.DetailLockerDto;
import com.ssafy.townbook.model.dto.LockerDto;
import com.ssafy.townbook.model.dto.response.FindListResponseDto;
import com.ssafy.townbook.model.dto.response.FindOneResponseDto;
import com.ssafy.townbook.model.dto.response.SaveOneResponseDto;
import com.ssafy.townbook.model.entity.DetailLocker;
import com.ssafy.townbook.model.entity.Locker;
import com.ssafy.townbook.model.repository.BookRepository;
import com.ssafy.townbook.model.repository.DetailLockerRepository;
import com.ssafy.townbook.model.repository.LockerRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
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
        JSONArray jsonArray = new JSONArray();
        Locker findLocker = lockerRepository.findLockerByLockerNo(lockerNo).get();

        for (int i =0; i<findLocker.getLockerBookCnt();i++){
            JSONObject jsonObject = new JSONObject();

            DetailLockerDto detailLockerDto = new DetailLockerDto(detailLockerRepository.findDetailLockerByDetailLockerNo(findLocker.getDetailLocker().get(i).getDetailLockerNo()).get());
            jsonObject.put("detailLocker", detailLockerDto);

//            책 이름, 디테일 보관함 번호
            jsonArray.add(jsonObject);
        }

//        return new FindOneResponseDto(new LockerDto(findLocker));
        return new FindOneResponseDto(jsonArray);
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
}

