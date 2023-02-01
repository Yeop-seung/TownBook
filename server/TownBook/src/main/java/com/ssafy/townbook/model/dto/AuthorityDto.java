package com.ssafy.townbook.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AuthorityDto {

    private String authorityName;

    @Builder
    public AuthorityDto(String authorityName) {
        this.authorityName = authorityName;
    }
}
