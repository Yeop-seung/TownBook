package hide information.townbook.model.dto;


import hide information.townbook.model.entity.Account;
import hide information.townbook.model.entity.File;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class AccountDto {
    
    private Long   accountNo;
    @NotNull
    private String accountEmail;
    
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

    private Set<AuthorityDto> authorityDtoSet;
    
    @Builder
    public AccountDto(Long accountNo, String accountEmail, String accountPw, String accountName, String accountAddress,
            String accountPhoneNumber, Integer accountGender, String accountNickname, String accountBirthDay,
            Set<AuthorityDto> authorityDtoSet) {
        this.accountNo          = accountNo;
        this.accountEmail       = accountEmail;
        this.accountPw          = accountPw;
        this.accountName        = accountName;
        this.accountAddress     = accountAddress;
        this.accountPhoneNumber = accountPhoneNumber;
        this.accountGender      = accountGender;
        this.accountNickname    = accountNickname;
        this.accountBirthDay    = accountBirthDay;
        this.authorityDtoSet    = authorityDtoSet;
    }
    
    @Builder
    public static AccountDto from(Account account) {
        if (account == null) {
            return null;
        }
        
        return AccountDto.builder()
                .accountNo(account.getAccountNo())
                .accountName(account.getAccountName())
                .accountPw(account.getAccountPw())
                .accountPhoneNumber(account.getAccountPhoneNumber())
                .accountBirthDay(account.getAccountBirthDay())
                .accountAddress(account.getAccountAddress())
                .accountEmail(account.getAccountEmail())
                .accountGender(account.getAccountGender())
                .accountNickname(account.getAccountNickname())
                .authorityDtoSet(account.getAuthorities().stream()
                        .map(authority -> AuthorityDto.builder().authorityName(
                                authority.getAuthorityName()).build())
                        .collect(Collectors.toSet()))
                .build();
    }
}
