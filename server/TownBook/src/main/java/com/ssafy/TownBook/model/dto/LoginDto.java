package com.ssafy.TownBook.model.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginDto {

    @NotNull
    @Size(min = 3, max = 50)
    private String account;

    @NotNull
    @Size(min = 3, max = 100)
    private String password;

    @Builder
    public LoginDto(String account, String password) {
        this.account = account;
        this.password = password;
    }
}
