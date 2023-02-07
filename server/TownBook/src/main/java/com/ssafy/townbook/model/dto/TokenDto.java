package com.ssafy.townbook.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenDto {
    
    private String token;

    private Long accountNo;
    
    @Builder
    public TokenDto(Long accountNo,String token) {
        this.accountNo = accountNo;
        this.token = token;
    }
}
