package com.ssafy.townbook.model.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DonateBookLogResponseDto {
    
    private Integer accountPoint;
    
    @Builder
    public DonateBookLogResponseDto(Integer accountPoint) {
        this.accountPoint = accountPoint;
    }
}
