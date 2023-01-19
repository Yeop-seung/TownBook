package com.ssafy.TownBook.model.dto;


import com.ssafy.TownBook.model.Entity.Account;
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

    @NotNull
    private String accountId;

    @NotNull
    private String accountPw;

    @NotNull
    private String accountName;

    @NotNull
    private String accountAddress;

    @NotNull
    private String accountPhoneNumber;

    @NotNull
    private String accountEmail;

    @NotNull
    private String accountNickname;

    @NotNull
    private String accountBirthDay;


    private Set<AuthorityDto> authorityDtoSet;

    @Builder
    public AccountDto(String accountId, String accountPw, String accountName, String accountAddress,
            String accountPhoneNumber, String accountEmail, String accountNickname,
            String accountBirthDay, Set<AuthorityDto> authorityDtoSet) {
        this.accountId = accountId;
        this.accountPw = accountPw;
        this.accountName = accountName;
        this.accountAddress = accountAddress;
        this.accountPhoneNumber = accountPhoneNumber;
        this.accountEmail = accountEmail;
        this.accountNickname = accountNickname;
        this.accountBirthDay = accountBirthDay;
        this.authorityDtoSet = authorityDtoSet;
    }

    @Builder


    public static AccountDto from(Account account) {
        if(account == null) return null;

        return AccountDto.builder()
                .accountId(account.getAccountId())
                .accountName(account.getAccountName())
                .accountPhoneNumber(account.getAccountPhoneNumber())
                .accountBirthDay(account.getAccountBirthday())
                .accountAddress(account.getAccountAddress())
                .accountEmail(account.getAccountEmail())
                .accountNickname(account.getAccountNickname())
                .authorityDtoSet(account.getAuthorities().stream()
                        .map(authority -> AuthorityDto.builder().authorityName(
                                authority.getAuthorityName()).build())
                        .collect(Collectors.toSet()))
                .build();
    }
}
