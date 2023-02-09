package com.ssafy.townbook.model.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ModifyAccountRequestDto {

    private Long accountNo;

    @NotNull
    private String accountPw;

    @NotNull
    private String accountAddress;

    @NotNull
    private String accountPhoneNumber;

    @NotNull
    private String accountNickname;

    @NotNull
    private String accountBirthDay;

    public ModifyAccountRequestDto(Long accountNo, String accountPw, String accountAddress, String accountPhoneNumber, String accountNickname, String accountBirthDay) {
        this.accountNo = accountNo;
        this.accountPw = accountPw;
        this.accountAddress = accountAddress;
        this.accountPhoneNumber = accountPhoneNumber;
        this.accountNickname = accountNickname;
        this.accountBirthDay = accountBirthDay;
    }
}


