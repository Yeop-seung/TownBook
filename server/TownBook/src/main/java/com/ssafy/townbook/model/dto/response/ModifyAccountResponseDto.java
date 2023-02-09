package com.ssafy.townbook.model.dto.response;

import com.ssafy.townbook.model.entity.Account;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ModifyAccountResponseDto {

    @NotNull
    private String accountName;

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

    @Builder
    public ModifyAccountResponseDto(Account account) {
        this.accountName = account.getAccountName();
        this.accountPw = account.getAccountPw();
        this.accountAddress = account.getAccountAddress();
        this.accountPhoneNumber = account.getAccountPhoneNumber();
        this.accountNickname = account.getAccountNickname();
        this.accountBirthDay = account.getAccountBirthDay();
    }
}


