package hide information.townbook.model.dto;

import hide information.townbook.model.entity.Account;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminDto {
    
    private Long    accountNo;
    private String  accountEmail;
    private String  accountName;
    private String  accountAddress;
    private String  accountPhoneNumber;
    private Integer accountGender;
    private String  accountNickname;
    private String  accountBirthDay;
    private Integer accountPoint;
    private Integer accountBookCnt;
    
    @Builder
    public AdminDto(Account account) {
        this.accountNo          = account.getAccountNo();
        this.accountEmail       = account.getAccountEmail();
        this.accountName        = account.getAccountName();
        this.accountAddress     = account.getAccountAddress();
        this.accountPhoneNumber = account.getAccountPhoneNumber();
        this.accountGender      = account.getAccountGender();
        this.accountNickname    = account.getAccountNickname();
        this.accountBirthDay    = account.getAccountBirthDay();
        this.accountPoint       = account.getAccountPoint();
        this.accountBookCnt     = account.getAccountBookCnt();
    }
}
