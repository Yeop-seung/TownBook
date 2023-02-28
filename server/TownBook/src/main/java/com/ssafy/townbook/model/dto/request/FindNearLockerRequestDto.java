package com.ssafy.townbook.model.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindNearLockerRequestDto {
    
    private Double userLatitude;
    private Double userLongitude;
    
    @Builder
    public FindNearLockerRequestDto(Double userLatitude, Double userLongitude) {
        this.userLatitude  = userLatitude;
        this.userLongitude = userLongitude;
    }
}
