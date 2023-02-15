package com.ssafy.townbook.model.dto;

import com.ssafy.townbook.model.entity.DetailLocker;
import com.ssafy.townbook.model.entity.Locker;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LockerDto {
    
    private Long                  lockerNo;
    private Integer               lockerBookCnt;
    private String                lockerRegion;
    private Double                lockerLatitude;
    private Double                lockerLongitude;
    private int                   lockerStorage;
    private Integer               lockerDistance;
    private List<DetailLockerDto> detailLocker = new ArrayList<>();
    
    @Builder
    public LockerDto(Locker locker) {
        this.lockerNo        = locker.getLockerNo();
        this.lockerBookCnt   = locker.getLockerBookCnt();
        this.lockerRegion    = locker.getLockerRegion();
        this.lockerLatitude  = locker.getLockerLatitude();
        this.lockerLongitude = locker.getLockerLongitude();
        
        // 연관 관계의 무한 참조를 방지하기 위해 DetailLockerDto 사용
        List<DetailLocker> findDetailLockers = locker.getDetailLocker();
        this.detailLocker = findDetailLockers.stream()
                .map(DetailLockerDto::new)
                .collect(Collectors.toList());
        
        // 보관함 저장 공간
        this.lockerStorage = 0;
        for (int i = 0; i < findDetailLockers.size(); i++) {
            if (findDetailLockers.get(i).getBookInDetailLocker() == ""
                    || findDetailLockers.get(i).getBookInDetailLocker() == null) {
                this.lockerStorage++;
            }
        }
    }
    
    @Builder
    public LockerDto(Locker locker, Double userLatitude, Double userLongitude) {
        this.lockerNo        = locker.getLockerNo();
        this.lockerBookCnt   = locker.getLockerBookCnt();
        this.lockerRegion    = locker.getLockerRegion();
        this.lockerLatitude  = locker.getLockerLatitude();
        this.lockerLongitude = locker.getLockerLongitude();
        
        // 연관 관계의 무한 참조를 방지하기 위해 DetailLockerDto 사용
        List<DetailLocker> findDetailLockers = locker.getDetailLocker();
        this.detailLocker = findDetailLockers.stream()
                .map(DetailLockerDto::new)
                .collect(Collectors.toList());
        
        // 보관함 저장 공간
        this.lockerStorage = 0;
        for (int i = 0; i < findDetailLockers.size(); i++) {
            if (findDetailLockers.get(i).getBookInDetailLocker() == ""
                    || findDetailLockers.get(i).getBookInDetailLocker() == null) {
                this.lockerStorage++;
            }
        }
        
        // distance
        /*
         * latitude 위도 36.3655
         * longitude 경도 127.3554
         * 두 위도간 도, 분, 초 차이 값에 각 거리를 곱하여 두 점간 수직거리를 계산
         * 두 경도간 도, 분, 초 차이 값에 각 거리를 곱하여 두 점간 수평거리를 계산
         * 직각 삼각형의 빗변 구하는 공식 사용
         * 경도 1도 : 111km, 1분 : 1.85km, 1초 : 30.8m
         * 위도 1도 : 88.8km, 1분 : 1.48, 1초 : 25m
         */
        
        // dummy locker ignore
        if (lockerLongitude != 0 || lockerLatitude != 0) {
            
            // 유저 경도 도 분 초
            String userLatitudeString = String.valueOf(userLatitude);
            
            String userLatitudeDString = userLatitudeString.substring(0, 2);
            Double userLatitudeDDouble = Double.parseDouble(userLatitudeDString);
            
            String userLatitudeMString = userLatitudeString.substring(3, 5);
            Double userLatitudeMDouble = Double.parseDouble(userLatitudeMString);
            
            String userLatitudeSString = userLatitudeString.substring(5, 7);
            Double userLatitudeSDouble = Double.parseDouble(userLatitudeSString);
            
            // 유저 위도 도 분 초
            String userLongitudeString = String.valueOf(userLongitude);
            
            String userLongitudeDString = userLongitudeString.substring(0, 3);
            Double userLongitudeDDouble = Double.parseDouble(userLongitudeDString);
            
            String userLongitudeMString = userLongitudeString.substring(4, 6);
            Double userLongitudeMDouble = Double.parseDouble(userLongitudeMString);
            
            String userLongitudeSString = userLongitudeString.substring(6, 8);
            Double userLongitudeSDouble = Double.parseDouble(userLongitudeSString);
            
            // 보관함 경도 도 분 초
            String lockerLatitudeString = String.valueOf(this.lockerLatitude);
            
            String lockerLatitudeDString = lockerLatitudeString.substring(0, 2);
            Double lockerLatitudeDDouble = Double.parseDouble(lockerLatitudeDString);
            
            String lockerLatitudeMString = lockerLatitudeString.substring(3, 5);
            Double lockerLatitudeMDouble = Double.parseDouble(lockerLatitudeMString);
            
            String lockerLatitudeSString = lockerLatitudeString.substring(5, 7);
            Double lockerLatitudeSDouble = Double.parseDouble(lockerLatitudeSString);
            
            // 보관함 위도 도 분 초
            String lockerLongitudeString = String.valueOf(this.lockerLongitude);
            
            String lockerLongitudeDString = lockerLongitudeString.substring(0, 3);
            Double lockerLongitudeDDouble = Double.parseDouble(lockerLongitudeDString);
            
            String lockerLongitudeMString = lockerLongitudeString.substring(4, 6);
            Double lockerLongitudeMDouble = Double.parseDouble(lockerLongitudeMString);
            
            String lockerLongitudeSString = lockerLongitudeString.substring(6, 8);
            Double lockerLongitudeSDouble = Double.parseDouble(lockerLongitudeSString);
            
            // 위도 latitude 기준 수직 거리 계산
            double verticalDistance = 0D;
            double verticalD        = 88.8D;
            double verticalM        = 1.48D;
            double verticalS        = 0.025D;
            verticalDistance = Math.abs(userLatitudeDDouble - lockerLatitudeDDouble) * verticalD
                    + Math.abs(userLatitudeMDouble - lockerLatitudeMDouble) * verticalM
                    + Math.abs(userLatitudeSDouble - lockerLatitudeSDouble) * verticalS;
            
            // 경도 longitude 기준 수평 거리 계산
            double horizontalDistance = 0D;
            double horizontalD        = 111D;
            double horizontalM        = 1.85D;
            double horizontalS        = 0.030D;
            horizontalDistance = Math.abs(userLongitudeDDouble - lockerLongitudeDDouble) * horizontalD
                    + Math.abs(userLongitudeMDouble - lockerLongitudeMDouble) * horizontalM
                    + Math.abs(userLongitudeSDouble - lockerLongitudeSDouble) * horizontalS;
            
            // 빗변 공식 A^2 + B^2 = C^2
            double squareDistance = verticalDistance * verticalDistance + horizontalDistance * horizontalDistance;
            
            this.lockerDistance = (int) Math.sqrt(squareDistance);
        } else {
            this.lockerDistance = 999;
        }
    }
}