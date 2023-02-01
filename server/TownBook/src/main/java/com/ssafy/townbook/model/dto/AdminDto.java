package com.ssafy.townbook.model.dto;

import com.ssafy.townbook.model.entity.Account;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AdminDto {
    
    private String accountEmail;
    private String accountPw;
    private String accountName;
    private String accountAddress;
    private String accountPhoneNumber;
    private Integer accountGender;
    private String accountNickname;
    private String accountBirthDay;
    
    public AdminDto(Account account) {
        this.accountEmail = account.getAccountEmail();
        this.accountPw = account.getAccountPw();
        this.accountName = account.getAccountName();
        this.accountAddress = account.getAccountAddress();
        this.accountPhoneNumber = account.getAccountPhoneNumber();
        this.accountGender = account.getAccountGender();
        this.accountNickname = account.getAccountNickname();
        this.accountBirthDay = account.getAccountBirthday();
    }
}
