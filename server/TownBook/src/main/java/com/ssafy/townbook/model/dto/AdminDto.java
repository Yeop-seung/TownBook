package com.ssafy.townbook.model.dto;

import com.ssafy.townbook.model.entity.Account;
import javax.persistence.Column;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Data
@NoArgsConstructor
public class AdminDto {
    
    private String accountEmail;
    private String accountName;
    private String accountAddress;
    private String accountPhoneNumber;
    private Integer accountGender;
    private String accountNickname;
    private String accountBirthDay;
    private Integer accountPoint;
    private Integer accountBookCnt;
    
    public AdminDto(Account account) {
        this.accountEmail = account.getAccountEmail();
        this.accountName = account.getAccountName();
        this.accountAddress = account.getAccountAddress();
        this.accountPhoneNumber = account.getAccountPhoneNumber();
        this.accountGender = account.getAccountGender();
        this.accountNickname = account.getAccountNickname();
        this.accountBirthDay = account.getAccountBirthDay();
        this.accountPoint = account.getAccountPoint();
        this.accountBookCnt = account.getAccountBookCnt();
    }
}
