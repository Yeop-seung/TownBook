package com.ssafy.townbook.model.dto.request;

import com.ssafy.townbook.model.dto.AuthorityDto;
import com.ssafy.townbook.model.entity.Account;
import com.ssafy.townbook.model.entity.File;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ModifyAccountRequestDto {

    private Long accountNo;

    @NotNull
    private String accountPw;

    @NotNull
    private String accountName;

    @NotNull
    private String accountAddress;

    @NotNull
    private String accountPhoneNumber;

    @NotNull
    private Integer accountGender;

    @NotNull
    private String accountNickname;

    @NotNull
    private String accountBirthDay;

    public ModifyAccountRequestDto(Long accountNo, String accountPw, String accountName, String accountAddress, String accountPhoneNumber, Integer accountGender, String accountNickname, String accountBirthDay) {
        this.accountNo = accountNo;
        this.accountPw = accountPw;
        this.accountName = accountName;
        this.accountAddress = accountAddress;
        this.accountPhoneNumber = accountPhoneNumber;
        this.accountGender = accountGender;
        this.accountNickname = accountNickname;
        this.accountBirthDay = accountBirthDay;
    }
}


